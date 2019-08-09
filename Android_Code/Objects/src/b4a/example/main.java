package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.agraham.byteconverter.ByteConverter _conv = null;
public static anywheresoftware.b4a.objects.UsbSerial _usb = null;
public static anywheresoftware.b4a.randomaccessfile.AsyncStreams _astreams = null;
public static String _mensaje_out = "";
public anywheresoftware.b4a.objects.LabelWrapper _timer_1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_10 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_11 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_12 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_13 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_14 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_15 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_16 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_17 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_18 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_19 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_20 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_21 = null;
public anywheresoftware.b4a.objects.LabelWrapper _timer_22 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edittext1 = null;
public b4a.example.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
int _dev = 0;
 //BA.debugLineNum = 37;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"Main_Layout\")";
mostCurrent._activity.LoadLayout("Main_Layout",mostCurrent.activityBA);
 //BA.debugLineNum = 49;BA.debugLine="If (usb.HasPermission(1)) Then	' Ver_2.4";
if ((_usb.HasPermission((int) (1)))) { 
 //BA.debugLineNum = 51;BA.debugLine="Dim dev As Int";
_dev = 0;
 //BA.debugLineNum = 54;BA.debugLine="dev = usb.Open(115200, 1)		' Ver_2.4";
_dev = _usb.Open(processBA,(int) (115200),(int) (1));
 //BA.debugLineNum = 58;BA.debugLine="If dev <> usb.USB_NONE Then";
if (_dev!=_usb.USB_NONE) { 
 //BA.debugLineNum = 63;BA.debugLine="astreams.Initialize(usb.GetInputStream,usb.Get";
_astreams.Initialize(processBA,_usb.GetInputStream(),_usb.GetOutputStream(),"Astreams");
 }else {
 //BA.debugLineNum = 65;BA.debugLine="Log(\"Error opening USB port 1\")";
anywheresoftware.b4a.keywords.Common.Log("Error opening USB port 1");
 };
 }else {
 //BA.debugLineNum = 68;BA.debugLine="usb.RequestPermission(1)  ' Ver_2.4";
_usb.RequestPermission((int) (1));
 };
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 82;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _astreams_newdata(byte[] _buffer) throws Exception{
String _command = "";
int _pos_box = 0;
boolean _bandera = false;
 //BA.debugLineNum = 99;BA.debugLine="Sub Astreams_NewData (Buffer() As Byte)";
 //BA.debugLineNum = 100;BA.debugLine="Dim command As String";
_command = "";
 //BA.debugLineNum = 101;BA.debugLine="Dim Pos_Box As Int";
_pos_box = 0;
 //BA.debugLineNum = 102;BA.debugLine="Dim bandera As Boolean";
_bandera = false;
 //BA.debugLineNum = 104;BA.debugLine="command =conv.StringFromBytes(Buffer, \"UTF8\")";
_command = _conv.StringFromBytes(_buffer,"UTF8");
 //BA.debugLineNum = 105;BA.debugLine="If command.Contains(\"a\") Then";
if (_command.contains("a")) { 
 //BA.debugLineNum = 107;BA.debugLine="Try";
try { //BA.debugLineNum = 108;BA.debugLine="Timer_1.Text = command.SubString2(1, command.In";
mostCurrent._timer_1.setText((Object)(_command.substring((int) (1),_command.indexOf("b"))));
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 112;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 };
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 96;BA.debugLine="mensaje_Out=\"a\"";
_mensaje_out = "a";
 //BA.debugLineNum = 97;BA.debugLine="Write_In_Arduino(mensaje_Out)";
_write_in_arduino(_mensaje_out);
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 30;BA.debugLine="Dim Timer_1, Timer_2, Timer_3, Timer_4, Timer_5,";
mostCurrent._timer_1 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_2 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_3 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_4 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_5 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_6 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_7 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_8 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_9 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_10 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_11 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_12 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_13 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_14 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim Timer_15, Timer_16, Timer_17, Timer_18, Timer";
mostCurrent._timer_15 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_16 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_17 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_18 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_19 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_20 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_21 = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._timer_22 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim EditText1 As EditText";
mostCurrent._edittext1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _label_click() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl_tag = null;
 //BA.debugLineNum = 87;BA.debugLine="Sub Label_Click";
 //BA.debugLineNum = 88;BA.debugLine="Dim lbl_tag As Label";
_lbl_tag = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 89;BA.debugLine="lbl_tag = Sender";
_lbl_tag.setObject((android.widget.TextView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 90;BA.debugLine="lbl_tag.Text = lbl_tag.Tag";
_lbl_tag.setText(_lbl_tag.getTag());
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim conv As ByteConverter";
_conv = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 19;BA.debugLine="Dim usb As UsbSerial";
_usb = new anywheresoftware.b4a.objects.UsbSerial();
 //BA.debugLineNum = 20;BA.debugLine="Dim astreams As AsyncStreams";
_astreams = new anywheresoftware.b4a.randomaccessfile.AsyncStreams();
 //BA.debugLineNum = 21;BA.debugLine="Dim mensaje_Out As String";
_mensaje_out = "";
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _write_in_arduino(String _ms) throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Sub Write_In_Arduino (Ms As String)";
 //BA.debugLineNum = 74;BA.debugLine="Ms = mensaje_Out";
_ms = _mensaje_out;
 //BA.debugLineNum = 75;BA.debugLine="astreams.Write(Ms.GetBytes(\"UTF8\"))";
_astreams.Write(_ms.getBytes("UTF8"));
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
}

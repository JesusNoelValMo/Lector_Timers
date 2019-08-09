# include "Timer.h"
Timer t;
int global_count, i;

//Grupo de pines para timers
int Trigger_Test_Pin_1 = 2;
int Trigger_Test_Pin_2 = 3;
int Group1_PinTimerDev[11] = {22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};
int Group2_PinTimerDev[11] = {33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
String ID_Char[22] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v"};
//Se crea la clase timer_divice, aqui se va a hacer la suma de los tiempos en los que seactivan los timers
class Timer_device
{
  private:
    //cuenta en seg o mseg
    boolean Pin_Timer_Status;
    unsigned long NextTimeToCheck = 0;
  public:
    int count;
    boolean is_finished;
    void Initialize_Pin(byte Pin_Timer)
    {
      pinMode(Pin_Timer,INPUT);
    //  Serial.println("Pin " + String(Pin_Timer) + "Inicializado");
    }
    void Read_n_sum_count(byte Pin_Timer, unsigned long TimeToCheck, int ID_timer)
    { 
      unsigned long currentTime = millis();
      if (currentTime >= NextTimeToCheck)
      {
        NextTimeToCheck = TimeToCheck + currentTime;
       Serial.println(currentTime);
       Serial.println(NextTimeToCheck);
        Pin_Timer_Status = digitalRead(Pin_Timer);
        if (Pin_Timer_Status == LOW)
        {
          count++;
          
          Serial.println("  ID" + ID_Char[ID_timer]  +":"+ String(count));
     
          is_finished = false;
        }
        else 
        {
          is_finished = true;
          Serial.println("LISTO");
        }
      }
    }
    //Se obtiene el conteo actual del timer
    int SendCount()
    {
      //Serial.print("ID02ID12ID32ID42ID52ID62ID72ID82");
      //Serial.flush();
     // return (count);
    }
};
//Primer juego de timers
Timer_device TDev1, TDev2, TDev3, TDev4, TDev5, TDev6, TDev7, TDev8, TDev9, TDev10, TDev11;
Timer_device TDevices1[11] = {TDev1, TDev2, TDev3, TDev4, TDev5, TDev6, TDev7, TDev8, TDev9, TDev10, TDev11} ;
//Segundo juego de timers
Timer_device TDev12, TDev13, TDev14, TDev15, TDev16, TDev17, TDev18, TDev19, TDev20, TDev121, TDev22;
Timer_device TDevices2[11] = {TDev12, TDev13, TDev14, TDev15, TDev16, TDev17, TDev18, TDev19, TDev20, TDev121, TDev22};
void setup() {
  Serial.begin(115200);
  t.every(1000, TDevices1[0].SendCount());
  for (i = 0; i <= 10; i++)
  {
    TDevices1[i].Initialize_Pin(Group1_PinTimerDev[i]);
    TDevices2[i].Initialize_Pin(Group2_PinTimerDev[i]);
  }
  pinMode(Trigger_Test_Pin_1,INPUT);
  pinMode(Trigger_Test_Pin_2,INPUT);
  
}
void loop() {
  if(digitalRead(Trigger_Test_Pin_1) == true){
    for(i = 0; i <= 10; i++){
      if (TDevices1[i].is_finished == false){
        TDevices1[i].Read_n_sum_count(Group1_PinTimerDev[i], 1000, i); 
      }
    }
  }
  else{
    for(i = 0; i <= 10; i++){
       TDevices1[i].count = 0;
       TDevices1[i].is_finished = false;
    }
  }
  if(digitalRead(Trigger_Test_Pin_2) == true){
    for(i = 0; i <= 10; i++){
      if (TDevices2[i].is_finished == false){
        TDevices2[i].Read_n_sum_count(Group2_PinTimerDev[i], 1000, (i + 11)); 
      }
    }
  }
  else{
    for(i = 0; i <= 10; i++){
       TDevices2[i].count = 0;
       TDevices2[i].is_finished = false;
       
    }
  }
 // t.update();
  //Serial.print("ASD");
}


//Grupo 2 de timers
#define PinTimerDev_12 33
#define PinTimerDev_13 34
#define PinTimerDev_14 35
#define PinTimerDev_15 36
#define PinTimerDev_16 37
#define PinTimerDev_17 38
#define PinTimerDev_18 39
#define PinTimerDev_19 40
#define PinTimerDev_20 41
#define PinTimerDev_21 42
#define PinTimerDev_22 43

//Se crea la clase timer_divice, aqui se va a hacer la suma de los tiempos en los que seactivan los timers
int global_count;
int Group1_PinTimerDev[11] = {22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};

boolean is_finished;
class Timer_device
{
  private:
  //cuenta en seg o mseg
    int count;
    boolean Pin_Timer_Status;

  public:
    void Initialize_Pin(byte Pin_Timer)
    {
      pinMode(Pin_Timer,INPUT);
    }
    void Read_n_sum_count(byte Pin_Timer, unsigned long TimeToCheck)
    { 
      unsigned long currentTime = millis();
      if (currentTime >= TimeToCheck){
        Pin_Timer_Status = digitalRead(Pin_Timer);
        if (Pin_Timer_Status == LOW)
        {
          count++;
          is_finished = false;
        }
        else {
          is_finished = true;
        }
    }
    }
    //Se obtiene el conteo actual del timer
    int GetCount()
    {
      return (count);
    }
  
};

//Primer juego de timers
Timer_device TDev1, TDev2, TDev3, TDev4, TDev5, TDev6, TDev7, TDev8, TDev9, TDev10, TDev11;
Timer_device TDevices1[11] = {TDev1, TDev2, TDev3, TDev4, TDev5, TDev6, TDev7, TDev8, TDev9, TDev10, TDev11} ;


//Segundo juego de timers
Timer_device TDev12, TDev13, TDev14, TDev15, TDev16, TDev17, TDev18, TDev19, TDev20, TDev121, TDev22;

void setup() {
  // put your setup code here, to run once:
TDevices1[1].Initialize_Pin(PinTimerDev_1);

}

void loop() {
  // put your main code here, to run repeatedly:

}

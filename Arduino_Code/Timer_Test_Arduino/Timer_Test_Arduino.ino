//Se crea la clase timer_divice, aqui se va a hacer la suma de los tiempos en los que seactivan los timers
int global_count, i;
//Grupo de pines para timers
int Trigger_Test_Pin_1 = 2;
int Trigger_Test_Pin_2 = 3;
int Group1_PinTimerDev[11] = {22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32};
int Group2_PinTimerDev[11] = {33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
boolean is_finished[11];
boolean is_finished2[11];
class Timer_device
{
  private:
    //cuenta en seg o mseg
    boolean Pin_Timer_Status;
    unsigned long NextTimeToCheck = 0;
  public:
    int count;
    void Initialize_Pin(byte Pin_Timer)
    {
      pinMode(Pin_Timer,INPUT);
      Serial.println("Pin " + String(Pin_Timer) + "Inicializado");
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
          Serial.println(count);
          is_finished[ID_timer] = false;
        }
        else 
        {
          is_finished[ID_timer] = true;
          Serial.println("LISTO");
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
Timer_device TDevices2[11] = {TDev12, TDev13, TDev14, TDev15, TDev16, TDev17, TDev18, TDev19, TDev20, TDev121, TDev22};
void setup() {
  for (i = 0; i <= 10; i++)
  {
    TDevices1[i].Initialize_Pin(Group1_PinTimerDev[i]);
    TDevices2[i].Initialize_Pin(Group2_PinTimerDev[i]);
  }
  pinMode(Trigger_Test_Pin_1,INPUT);
  pinMode(Trigger_Test_Pin_2,INPUT);
  Serial.begin(115200);
}
void loop() {
  if(digitalRead(Trigger_Test_Pin_1) == true){
    for(i = 0; i <= 10; i++){
      if (is_finished[i] == false){
        TDevices1[i].Read_n_sum_count(Group1_PinTimerDev[i], 1000, i); 
      }
    }
  }
  else{
    for(i = 0; i <= 10; i++){
       TDevices1[i].count = 0;
       is_finished[i] = false;
    }
  }
  if(digitalRead(Trigger_Test_Pin_2) == true){
    for(i = 0; i <= 10; i++){
      if (is_finished[i] == false){
        TDevices2[i].Read_n_sum_count(Group2_PinTimerDev[i], 1000, i); 
      }
    }
  }
  else{
    for(i = 0; i <= 10; i++){
       TDevices2[i].count = 0;
       is_finished[i] = false;
       
    }
  }
  //Serial.print("ASD");
}

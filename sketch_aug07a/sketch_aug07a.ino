unsigned long cuenta = 4294967290;
int i;
void setup() {
  // put your setup code here, to run once:
Serial.begin(115200);
}

void loop() {
  // put your main code here, to run repeatedly:
for(i = 0; i <= 5; i++){
   cuenta++;
  Serial.println(cuenta);
  delay(200);
}
}

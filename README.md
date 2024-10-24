#!/bin/bash

# Número de repetições
ITERATIONS=50

for ((i=1; i<=ITERATIONS; i++))
do
  echo "Execução $i"
  adb logcat -c  # Limpa os logs
  adb shell monkey -p com.seupacote -c android.intent.category.LAUNCHER 1

  # Captura o tempo do início
  adb shell date +"%s%3N" | tee -a start_time_$i.log

  sleep 5

  # Captura o tempo do final
  adb shell date +"%s%3N" | tee -a end_time_$i.log

  adb shell am force-stop com.seupacote
done

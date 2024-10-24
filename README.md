#!/bin/bash

# Número de repetições
ITERATIONS=50

# Step 1: Throttle CPU before starting the loop
echo "Throttling CPU to simulate a slower device..."
# Example: Setting a lower CPU frequency (use a lower available frequency)
adb shell "echo 600000 > /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq"
# Pause to ensure the changes take effect
sleep 5

# Step 2: Run the loop with the CPU throttled
for ((i=1; i<=ITERATIONS; i++))
do
  echo "Execução $i"
  # Clear logs if needed
  # adb logcat -c
  
  # Launch the app using monkey
  adb shell monkey -p com.itau -c android.intent.category.LAUNCHER 1

  # Capture the start time
  adb shell date +"%s%3N" | tee -a start_time_$i.log

  # Wait for 30 seconds to simulate a slower operation
  sleep 30

  # Capture the end time
  adb shell date +"%s%3N" | tee -a end_time_$i.log

  # Force-stop the app
  adb shell am force-stop com.itau
done

# Step 3: Reset CPU to default speed after completion
echo "Resetting CPU frequency to default..."
adb shell "echo 1500000 > /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq"

# Useful commands for the Raspberry Pi

## Setting up wifi from the command line

- [Setting up wifi via the command line](https://www.raspberrypi.org/documentation/configuration/wireless/wireless-cli.md)

Conenct your pi via HDMI with a monitor or open an new VPN connection (while in the same network). Now, open a terminal on your pi and type the following:

```sudo nano /etc/wpa_supplicant/wpa_supplicant.conf```

This opens the nano editor and load the `wpa_supplicant.conf` file. Here, you can specifiy your wifi connections. To add your own home wifi, simply add a new entry such as:

```
network={
    ssid="<your-ssid>"
    psk="<your-key>"
}
```

In addition, you can set the priority for this network to 1 to make sure it always connects when the network is available:

```
network={
    ssid="<your-ssid>"
    psk="<your-key>"
    priority=1
}
```

Close the nano editor with `Ctrl + X` and confirm that you want to save your changes with `Y`.

## Manually starting the node.js application

The application should start when you boot the Raspberry Pi. Howevery, if no sensor values are published you can start the script manually. Connect to your Pi using an HDMI cable or via VPN (while in the same network). Open a new terminal:

### Kill a potentially running process:

```
ps -xa | grep node
```

The PID of the process is shown to the left of the table. 

```
sudo kill <pid>
```

Replace `<pid>` with the PID you retrieved in the previous step.

### Start the challenge script

```
cd workspace/tinkerforge-helper/examples
node challenge2b.js
```

All connected devices should be found and the RGB light should blink when a new value is fired.
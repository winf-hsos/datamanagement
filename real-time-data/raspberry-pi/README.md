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

## Modify the node application to add a new PubNub account

Sometime you want to change the PubNub account where the sensor values are being published to. Or sometimes, as in the case of challenge 2b, you need to add an additional PubNub account and publish to both. Doing that is easy if you know what you are doing. The following describes the necessary steps.

## Step 1: Open the JS File in a text editor on your Pi

Connect to the UI of Raspberry Pi, either by connecting a monitor via HDMI, or using a VNC client.

Once you are connected to the UI, you can open the file browser, navigate to the location of the JS file you want to edit, and right-click on the file. Now choose "Text Editor" from the context menu.

![How to open a text editor on Raspberry Pi](/media/raspberry-pi-open-text-editor.gif)

The next step depends on what you are trying to do. Goto step 2a if you want to change the PubNub account where your sensor data is sent. Goto step 2b if you want to add one or more PubNub accounts and send the values to both.

## Step 2a: Set the Publish/Subscriber Key

## Step 2b: Add another PubNub account
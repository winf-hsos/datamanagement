## Useful commands for the Raspberry Pi

- [Setting up wifi via the command line](https://www.raspberrypi.org/documentation/configuration/wireless/wireless-cli.md)

Open a terminal on your pi and type the following:

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

Close the nano editor with `Ctrl + X` and confirm that you want to save your changes.


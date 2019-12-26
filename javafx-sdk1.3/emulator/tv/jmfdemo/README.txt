This directory contains a channel switching demo, configurable with a property
file. The default configuration enables switching between unicast and
multicast streams on ports 1234, 1235 and 1236.

To run the application, type the following:

../bin/cvm -cp jmfdemo.jar jmfdemo.Main jmfdemo.properties

Use the arrow keys to navigate the menus and play video.

The application requires data to be streamed to it. If you have the VideoLAN
application, you can easily use it to set up a streaming source, as follows:

vlc -L --sout "#std{access=udp,mux=ts,dst=<IP address>:<port>}" <media file>

For example,

vlc -L --sout "#std{access=udp,mux=ts,dst=239.0.0.1:1234}" myvideo.mpeg

for multicast video sent to port 1234 on 239.0.0.1.

The file jmfdemo.properties can be edited to suit your environment. Valid URLs
for media sources are in the form

udp://<IP address>:<port>

where <IP address> is the address to which the media is being streamed, or a
single "@" symbol for the local host. <port> is the port to which data is
streamed.

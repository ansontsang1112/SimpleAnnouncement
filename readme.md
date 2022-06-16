A plugin for simple announcement and broadcast

#### Download
[Click here to download SimpleAnnouncement](http://res.hypernology.com/mcplugins/SimpleAnnouncement-Beta1.0.jar)

#### Installation
1. Download and put the plugin into /plugins/
2. Restart or load the plugin into the server
3. Edit the configuration in the plugin
4. Happy to use

#### Requirement
**Java 17** or above.<br>
Minecraft Server with **1.18 or above**.

#### Commands
* Main command: /sap

| Sub-Command              | Description                              | Permission             |
|--------------------------|------------------------------------------|------------------------|
| reload                   | Reload the plugin                        | sap.admin (Default OP) |
| announce < message >     | Broadcast a message to all online player | sap.admin (Default OP) |
| send < ign > < message > | Send a private admin message to a player | sap.admin (Default OP) |


#### config.yml
```yml
# Configuration for Simple Announcement Plugin

settings:
  enable: false # Enable the auto announcement
  next-announcement-period: 30 #in seconds
  announcement-prefix: "[Simple Announcement]"
  broadcast-prefix: "&c[&lBroadcast]&f"

message:
  broadcast: "%b_prefix% Message From %admin%:&f %message%" # %XXX% is the placeholders. Don't change.
  p2p: "Admin message from %admin%: %message%" # %XXX% is the placeholders. Don't change.
  delivered: "Message sent successfully"
  permission-denied: "Permission Denied"
  player-not-found: "Player Not Found"

announcements:
  - "I am a test announcement"
  - "I am a test announcement 2"

```
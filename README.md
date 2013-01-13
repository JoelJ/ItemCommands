Item Commands - Minecraft Plugin
--------------------------------
Allows you to define a list of items that run commands defined in the config.yml file.

Simple Example: Turn the compass into a portal via NiftyWarp.

plugins/ItemCommands/config.yml:

    items:
        townPortalWarp:
            item: 345
            actions:
            - LEFT_CLICK_AIR
            - LEFT_CLICK_BLOCK
            commands:
            - home
        townPortalSet:
            item: 345
            actions:
            - RIGHT_CLICK_AIR
            - RIGHT_CLICK_BLOCK
            commands:
            - sethome

If you have the NiftyWarp plugin installed, any user with permissions to run that command can use the compass to set their home (right click) and teleport to that defined location (left click).

The keys "townPortalWarp" and "townPortalSet" are just strings. It doesn't matter what you call them.

As defined above, the command will be executed even if they don't have permissions. To prevent ItemCommands from even trying to run that command, we can specify the permissions (or even create new permissions) doing the following:

    items:
        townPortalWarp:
            ...
            permission: some.permission.here
        ...

You can even have it execute multiple commands:
    items:
        townPortalWarp:
            ...
            commands:
            - home
            - someother command
            ...

Hope you enjoy!


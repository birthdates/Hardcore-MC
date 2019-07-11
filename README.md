# Hardcore-MC
Ability to have hardcore mode in multiplayer, saves with mongodb support

# Permissions
hardcoremc.bypass - Ability to bypass the hardcore ban
hardcoremc.admin - Ability to use admin commands

# Commands
/difficulty <peaceful|normal|hard|> - Change the difficulty
/deathunban <uuid> - Remove a death ban from a uuid
  
 # Configuration
 ```yaml
difficulty: HARD
kick-message: '&c&lYou have died on hardcore mode and can no longer join.'
database:
  username: ''
  password: ''
  ip: localhost:27017
  name: hardcoremc
```
 

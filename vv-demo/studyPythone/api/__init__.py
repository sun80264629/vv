from wxpy import *
bot = Bot(cache_path=True)

user = User(bot)
user.UserName = "wn520702"
#found = bot.groups().search('孩子')

bot.add_friend(user,"你好")
embed()
print("Hello, World!");

# bot
# bot.friends() 会话
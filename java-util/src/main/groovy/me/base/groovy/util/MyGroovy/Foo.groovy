package me.base.groovy.util.MyGroovy

def sharedData = new Binding()
def shell = new GroovyShell()
def now = new Date()

sharedData.setProperty("text", "I am shared data!")
sharedData.setProperty("data", now)
String result = shell.evaluate('"At $date, $text"')
assert result == "At $now, I am shared data!"
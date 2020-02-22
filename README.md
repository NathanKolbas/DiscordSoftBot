# UNL Software Engineering Discord Bot

## Libraries
* https://gist.github.com/k3kdude/fba6f6b37594eae3d6f9475330733bdb
* https://app.quicktype.io/

## Parsing dates in Java example code
##### Not sure if we'll need it in the future but just in case.
```
DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
 try {
     Date date = dateFormatter.parse("2020-02-20T22:00:00Z");
     System.out.println(date.getTime() - new Date().getTime());
     System.out.println(date);
     System.out.println(new Date(date.getTime() - TimeUnit.DAYS.toMillis(7)));
     System.out.println((date.getTime() - TimeUnit.DAYS.toMillis(1)) - new Date().getTime());

     // Even though the Date object is reporting in CST it is actually in UTC time.
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
     format.setTimeZone(TimeZone.getTimeZone("UTC"));
     System.out.println(format.format(new Date()));
     System.out.println(new Date());
 } catch (ParseException e) {
     e.printStackTrace();
 }


 long delay = ChronoUnit.MILLIS.between(LocalTime.now(), LocalTime.now().plusSeconds(10));
 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
 scheduler.schedule(() -> call_me(SOFT161_ID), delay, TimeUnit.MILLISECONDS);
```

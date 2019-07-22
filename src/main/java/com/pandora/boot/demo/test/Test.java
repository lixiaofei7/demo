//package com.pandora.boot.demo.test;
//
//import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.stream.Collectors;
//
//public class Test {
//
//    public static void main(String[] args) throws  Exception{
//       /* long now = System.currentTimeMillis();
//        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//         String  startDate = sdfOne.format(now)+" 00:00:00";
//         System.out.println(startDate);*/
//
//
//      // List list = searchMonthDay4("2019-06-05","2019-06-11");
//     //  System.out.println(list);
//
//
//
//
//        Double v = null;
//        //v = String.valueOf((int)((Math.random()*9+1)*100000));
//
//      /*  v = Math.random()*9;
//        System.out.println(v);
//
//        ThreadFactory factory = r-> new Thread(r,"sss"+r.hashCode());
//        ExecutorService service = new ThreadPoolExecutor(1,1,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<>(100),factory);
//
//        service.execute(()->{
//            System.out.println(Thread.currentThread().getName());
//        });*/
//
//
//      List<Map> list = new ArrayList<>();
//      for(int i = 0;i<10;i++){
//          Map map = new HashMap();
//          map.put("id",i);
//          map.put("score",new Random().nextInt(100));
//          list.add(map);
//      }
//
//        System.out.println(list);
//
//      List<Map> filters = list.stream().filter(m->(int)m.get("score")>60).sorted(Comparator.comparing(m->(int)m.get("score"))).collect(Collectors.toList());
//      System.out.println(filters);
//
//    }
//
//    public static List<String> searchMonthDay4(String start, String end) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        List<String> list = new ArrayList<>();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(sdf.parse(start));
//
//        Calendar cal2 = Calendar.getInstance();
//        cal2.setTime(sdf.parse(end));
//
//        while (!cal2.getTime().equals(cal.getTime())) {
//            list.add(sdf.format(cal.getTime()));
//            cal.add(Calendar.DATE, 1);
//        }
//        list.add(sdf.format(cal.getTime()));
//        cal.add(Calendar.DATE, 1);
//
//        return list;
//    }
//}

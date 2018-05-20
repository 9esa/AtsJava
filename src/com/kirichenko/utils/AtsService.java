package com.kirichenko.utils;

/**
 * Created by user on 02.04.17.
 */
public class AtsService {

//    public static void startServiceClientAgainLast(){
//
//        HttpServer server = null;
//        try {
//            server = HttpServer.create(new InetSocketAddress(12106), 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        server.createContext("/clientRequestAgainLast", new HttpHandler() {
//            public void handle(HttpExchange httpExchange) throws IOException {
//
//                String response = "";
//                String sQuery = httpExchange.getRequestURI().getQuery();
//                String sNumberOfRequest = sQuery.substring(sQuery.indexOf("input_result=") + 13, sQuery.indexOf("numa=") - 1);
//
//                if(sNumberOfRequest.length() == 0){
//                    //Возвращаем то что клиент ничего не ввел
//                    response = "{ \"returned_code\": 0}";
//                }else if(sNumberOfRequest.length() < 4){
//                        //Возвращаем то что клиент ввел неправильный код
//                        response = "{ \"returned_code\": 0}";
//                }else{
//
//
//                    Calendar cal = Calendar.getInstance();
//                    cal.clear(Calendar.HOUR_OF_DAY);
//                    cal.clear(Calendar.HOUR);
//                    cal.clear(Calendar.AM_PM);
//                    cal.clear(Calendar.MINUTE);
//                    cal.clear(Calendar.SECOND);
//                    cal.clear(Calendar.MILLISECOND);
//                    Date oDate = cal.getTime();
//
//                    List listOfOrders = new ArrayList();
//                    try {
//                        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//                        String hql = "from TradeOrderEntity sTradeOrder INNER JOIN sTradeOrder.tradeByTradeId tradeOrder where tradeOrder.dateOfTrade = :tradeDate and sTradeOrder.id =:idTrade";
//                        listOfOrders = session.createQuery(hql)
//                                .setParameter("idTrade",  Integer.valueOf(sNumberOfRequest))
//                                .setParameter("tradeDate", oDate)
//                                .list();
//                    }catch (Exception ex){
//                        System.out.println(ex);
//                    }
//
//                    response = "{ \"returned_code\": 1}";
//
//                    if(listOfOrders.size() > 0){
//                        String sValuePhone = ((TradeOrderEntity) listOfOrders.get(0)).getClientByUserId().getPhoneNumber();
//                        response = "{ \"phones\": [\"" + sValuePhone +"\"]}";
//                    }
//
//                }
//
//                //       String response="{\"fgdgfdggdfgf\" : [\" 79818926024 \"] , \"text\" : \" TEXT\" }";
//                httpExchange.sendResponseHeaders(200, response.length());
//                OutputStream os = httpExchange.getResponseBody();
//                os.write(response.getBytes());
//                os.close();
//            }
//        });
//        server.start();
//    }
//
//
//    public static void startServiceClientAgain(){
//
//        HttpServer server = null;
//        try {
//            server = HttpServer.create(new InetSocketAddress(12105), 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        server.createContext("/clientRequestAgain", new HttpHandler() {
//            public void handle(HttpExchange httpExchange) throws IOException {
//
//                String response = "";
//                String sQuery = httpExchange.getRequestURI().getQuery();
//                String sNumberOfRequest = sQuery.substring(sQuery.indexOf("input_result=") + 13, sQuery.indexOf("numa=") - 1);
//
//                if(sNumberOfRequest.length() == 0){
//                    //Возвращаем то что клиент ничего не ввел
//                    response = "{ \"returned_code\": 0}";
//                }else if(sNumberOfRequest.length() < 4){
//
//                    if(sNumberOfRequest.equals("0")){
//                        response = "{ \"returned_code\": 9}";
//                    }else{
//                        //Возвращаем то что клиент ввел неправильный код
//                        response = "{ \"returned_code\": 0}";
//                    }
//                }else{
//
//                    Calendar cal = Calendar.getInstance();
//                    cal.clear(Calendar.HOUR_OF_DAY);
//                    cal.clear(Calendar.HOUR);
//                    cal.clear(Calendar.AM_PM);
//                    cal.clear(Calendar.MINUTE);
//                    cal.clear(Calendar.SECOND);
//                    cal.clear(Calendar.MILLISECOND);
//                    Date oDate = cal.getTime();
//
//                    List listOfOrders = new ArrayList();
//                    try {
//                        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//                        String hql = "from TradeOrderEntity sTradeOrder INNER JOIN sTradeOrder.tradeByTradeId tradeOrder where tradeOrder.dateOfTrade = :tradeDate and sTradeOrder.id =:idTrade";
//                        listOfOrders = session.createQuery(hql)
//                                .setParameter("idTrade",  Integer.valueOf(sNumberOfRequest))
//                                .setParameter("tradeDate", oDate)
//                                .list();
//                    }catch (Exception ex){
//                        System.out.println(ex);
//                    }
//
//                    response = "{ \"returned_code\": 0}";
//
//                    if(listOfOrders.size() > 0){
//                        String sValuePhone = ((TradeOrderEntity) listOfOrders.get(0)).getClientByUserId().getPhoneNumber();
//                        response = "{ \"phones\": [\"" + sValuePhone +"\"]}";
//                    }
//
//                }
//
//                httpExchange.sendResponseHeaders(200, response.length());
//                OutputStream os = httpExchange.getResponseBody();
//                os.write(response.getBytes());
//                os.close();
//            }
//        });
//        server.start();
//    }
//
//
//    public static void startServiceClient(){
//
//        HttpServer server = null;
//        try {
//            server = HttpServer.create(new InetSocketAddress(12103), 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        server.createContext("/clientRequest", new HttpHandler() {
//            public void handle(HttpExchange httpExchange) throws IOException {
//
//                String response = "";
//                String sQuery = httpExchange.getRequestURI().getQuery();
//                String sNumberOfRequest = sQuery.substring(sQuery.indexOf("input_result=") + 13, sQuery.indexOf("numa=") - 2);
//
//                if(sNumberOfRequest.length() == 0){
//                    //Возвращаем то что клиент ничего не ввел
//                    response = "{ \"returned_code\": 0}";
//                }else if(sNumberOfRequest.length() < 4){
//                    //Возвращаем то что клиент ввел неправильный код
//                    response = "{ \"returned_code\": 8}";
//                }else{
//
//                    Calendar cal = Calendar.getInstance();
//                    cal.clear(Calendar.HOUR_OF_DAY);
//                    cal.clear(Calendar.HOUR);
//                    cal.clear(Calendar.AM_PM);
//                    cal.clear(Calendar.MINUTE);
//                    cal.clear(Calendar.SECOND);
//                    cal.clear(Calendar.MILLISECOND);
//                    Date oDate = cal.getTime();
//
//                    List listOfClient = new ArrayList();
//                    try {
//                        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//                        String hql = "from TradeOrderEntity sTradeOrder INNER JOIN sTradeOrder.tradeByTradeId tradeOrder where tradeOrder.dateOfTrade = :tradeDate and sTradeOrder.id =:idTrade";
//                        listOfClient = session.createQuery(hql)
//                                .setParameter("idTrade",  Integer.valueOf(sNumberOfRequest))
//                                .setParameter("tradeDate", oDate)
//                                .list();
//                    }catch (Exception ex){
//                        System.out.println(ex);
//                    }
//
//                    response = "{ \"returned_code\": 8}";
//
//                    if(listOfClient.size() > 0){
//                        String sValuePhone = ((TradeOrderEntity) listOfClient.get(0)).getClientByUserId().getPhoneNumber();
//                        response = "{ \"phones\": [\"" + sValuePhone +"\"]}";
//                    }
//
//                }
//
//                httpExchange.sendResponseHeaders(200, response.length());
//                OutputStream os = httpExchange.getResponseBody();
//                os.write(response.getBytes());
//                os.close();
//
//            }
//        });
//        server.start();
//    }
//
//    public static void startServiceWhoCalls(){
//        HttpServer server = null;
//        try {
//            server = HttpServer.create(new InetSocketAddress(12102), 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        server.createContext("/whoCalls", new HttpHandler() {
//            public void handle(HttpExchange httpExchange) throws IOException {
//
//                String response = "";
//
//                String sQuery = httpExchange.getRequestURI().getQuery();
//                String sPhoneNumber = sQuery.substring(sQuery.indexOf("numa=") + 5);
//
//                Session session = HibernateSessionFactory.getSessionFactory().openSession();
//                String hql = "from DriverEntity s where s.phone = :phone";
//                List listOfDrivers = session.createQuery(hql)
//                        .setParameter("phone", sPhoneNumber)
//                        .list();
//                session.close();
//
//                boolean bIsDriver = false;
//                if(listOfDrivers.size() > 0){
//                    bIsDriver = true;
//                }
//
//                //todo убрать отрицание
//                if(bIsDriver){
//                    //Активация меню выбран водитель
//                    response = "{ \"returned_code\": 2}";
//                }else{
//                    //Активация меню выбран клиент
//                    response = "{ \"returned_code\": 1}";
//                }
//
//                httpExchange.sendResponseHeaders(200, response.length());
//                OutputStream os = httpExchange.getResponseBody();
//                os.write(response.getBytes());
//                os.close();
//
//                writeInformation(sPhoneNumber, listOfDrivers);
//
//            }
//        });
//
//        server.start();
//    }
//
//    private static void writeInformation(String sPhone,  List listOfDrivers){
//
//        JournalCallsEntity journalCallsEntity = new JournalCallsEntity();
//
//        if(!(listOfDrivers.size() > 0)){
//            Session session = HibernateSessionFactory.getSessionFactory().openSession();
//            String hql = "from ClientEntity s where s.phoneNumber = :sPhone";
//            listOfDrivers = session.createQuery(hql)
//                    .setParameter("sPhone", sPhone)
//                    .list();
//            session.close();
//
//            journalCallsEntity.setTypeCalls(Constant.CALL_CLIENT);
//
//            if(listOfDrivers.size()>0){
//                journalCallsEntity.setClientByClientId((ClientEntity) listOfDrivers.get(0));
//            }else{
//                ClientEntity oClientEntity = new ClientEntity();
//                oClientEntity.setUserFio("Не определено");
//                oClientEntity.setBornDate(new Date());
//                oClientEntity.setDateOfCreate(new Date());
//                oClientEntity.setPhoneNumber(sPhone);
//                session = Main.getSession();
//                session.beginTransaction();
//                session.save(oClientEntity);
//                session.getTransaction().commit();
//                session.close();
//                journalCallsEntity.setClientByClientId(oClientEntity);
//            }
//
//        }else{
//            journalCallsEntity.setTypeCalls(Constant.CALL_DRIVER);
//            journalCallsEntity.setDriverByDriverId((DriverEntity) listOfDrivers.get(0));
//        }
//        Session session = Main.getSession();
//        session.beginTransaction();
//        journalCallsEntity.setDateCalls(new Date());
//        session.save(journalCallsEntity);
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    public static void  startServiceDriver(){
//        HttpServer server = null;
//        try {
//            server = HttpServer.create(new InetSocketAddress(12104), 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        server.createContext("/driverRequest", new HttpHandler() {
//            public void handle(HttpExchange httpExchange) throws IOException {
//
//                String response = "";
//                String sQuery = httpExchange.getRequestURI().getQuery();
//
//                String sNumberOfRequest = sQuery.substring(sQuery.indexOf("input_result=")+13);
//
//                if(sNumberOfRequest.length() == 0){
//                    response = "{ \"returned_code\": 1}";
//                }else {
//                         List listOfOrders = new ArrayList();
//                    try {
//
//                        Calendar cal = Calendar.getInstance();
//                        cal.clear(Calendar.HOUR_OF_DAY);
//                        cal.clear(Calendar.HOUR);
//                        cal.clear(Calendar.AM_PM);
//                        cal.clear(Calendar.MINUTE);
//                        cal.clear(Calendar.SECOND);
//                        cal.clear(Calendar.MILLISECOND);
//                        Date oDate = cal.getTime();
//
//                        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//                        String hql = "from TradeOrderEntity sTradeOrder INNER JOIN sTradeOrder.tradeByTradeId tradeOrder where tradeOrder.dateOfTrade = :tradeDate and sTradeOrder.numberOfTrade =:numberOfTrade";
//                        listOfOrders = session.createQuery(hql)
//                                .setParameter("tradeDate",  oDate)
//                                .setParameter("numberOfTrade",  Integer.valueOf(sNumberOfRequest))
//                                .list();
//                    }catch (Exception ex){
//                        System.out.println(ex);
//                    }
//
//                    String sValuePhone = ((TradeOrderEntity) listOfOrders.get(0)).getClientByUserId().getPhoneNumber();
//
//                    response = "{ \"phones\": [\"" + sValuePhone +"\"]}";
//                }
//
//                httpExchange.sendResponseHeaders(200, response.length());
//                OutputStream os = httpExchange.getResponseBody();
//                os.write(response.getBytes());
//                os.close();
//
//            }
//        });
//
//        server.start();
//    }
//

}

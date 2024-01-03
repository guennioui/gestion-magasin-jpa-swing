package ma.emsi.test;

public class main {
    public static void main(String[] args) {
        clt clt = new clt();
        clt.setNom("GUENNIOUI");
        clt.setPrenom("ABDALLAH");
        clt.setTelephone("0762729034");
        clt.setId(clt.generateId());
        System.out.println(clt.toString());
    }
}

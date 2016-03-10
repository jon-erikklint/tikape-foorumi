package tikape.tikape.foorumi;

public class Main {
    
    public static void main(String[] args) throws Exception{
        System.out.println("moi");
        
        try{
        Alustaja ia = new Alustaja();
        ia.alustaSql();
        ia.alustaKuuntelijat();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
    }
    
}

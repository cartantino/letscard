import com.letscard.jpa.service.ServiceFactory;

public class Main {
    public static void main(String[] args) {
        //Helper.dropDatabase();
        //Helper.populateDatabase(2);
        System.out.print(ServiceFactory.getInstance().getUserService().getUserStandardCard(5L));
        System.out.print(ServiceFactory.getInstance().getUserService().getUserSharablecard(5L));

    }
}

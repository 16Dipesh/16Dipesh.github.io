package mra.com.tastyfoodcafe;

public class specialOffers {
    public specialOffers(String id, String sndfeed, String images, String productprise) {

    }

    public class SpecialOffers
    {
        String id,offername,offerday,image;

        public SpecialOffers()
        {}

        public SpecialOffers(String id, String offername, String offerday,String image) {
            this.id = id;
            this.offername = offername;
            this.offerday = offerday;
            this.image=image;
        }

        public String getId() {
            return id;
        }

        public String getOffername() {
            return offername;
        }

        public String getOfferday() {
            return offerday;
        }

        public String getImage() {
            return image;
        }
    }
}

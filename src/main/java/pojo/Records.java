package pojo;

public class Records {

    private String hotelnames;
    private String hotelprices;

    public Records(String hotelnames, String hotelprices) {
        this.hotelnames = hotelnames;
        this.hotelprices = hotelprices;
    }

    /*public int getId() {
        return id;
    }*/

   /* public void setId(int id) {
        this.id = id;
    }*/

    public String getHotelnames() {
        return hotelnames;
    }

    public void setHotelnames(String hotelnames) {
        this.hotelnames = hotelnames;
    }

    public String getHotelprices() {
        return hotelprices;
    }

    public void setHotelprices(String hotelprices) {
        this.hotelprices = hotelprices;
    }

    /*@Override
    *//*public String toString() {
        return "records{" +
                "id=" + id +
                ", hotelnames='" + hotelnames + '\'' +
                ", hotelprices='" + hotelprices + '\'' +
                '}';
    }*/
}

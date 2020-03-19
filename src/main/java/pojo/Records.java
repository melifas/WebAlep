package pojo;

public class Records {

    private String hotelnames;
    private double hotelprices;

    public Records(String hotelnames, double hotelprices) {
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

    public double getHotelprices() {
        return hotelprices;
    }

    public void setHotelprices(double hotelprices) {
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

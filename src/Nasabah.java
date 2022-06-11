public class Nasabah {
    private String NoRek;
    private String NamaNsb;
    private long Saldo = 500000;
    private int NoKartu;
    private int PIN;
    private String TglDaftar;
 

    public Nasabah() {
    }

    public Nasabah(String NoRek, String NamaNsb) {
        this.NoRek=NoRek;
        this.NamaNsb = NamaNsb;
        this.Saldo = 500000;
    }

    public Nasabah(String NoRek, String NamaNsb, long Saldo) {
        this.NoRek = NoRek;
        this.NamaNsb = NamaNsb;
        this.Saldo = Saldo;
        //setiap kali tambah data nasabah, tgl daftar akan di set hari ini
        this.TglDaftar = java.time.LocalDate.now().toString();
    }

    public Nasabah(String NoRek, String NamaNsb, long Saldo, int NoKartu, int PIN, String TglDaftar) {
        this.NoRek = NoRek;
        this.NamaNsb = NamaNsb;
        this.Saldo = Saldo;
        this.NoKartu = NoKartu;
        this.PIN = PIN;
        this.TglDaftar = TglDaftar;
    }

    public String getNoRek() {
        return this.NoRek;
    }

    public void setNoRek(String NoRek) {
        this.NoRek = NoRek;
    }

    public String getNamaNsb() {
        return this.NamaNsb;
    }

    public void setNamaNsb(String NamaNsb) {
        this.NamaNsb = NamaNsb;
    }

    public long getSaldo() {
        return this.Saldo;
    }

    public void setSaldo(long Saldo) {
        this.Saldo = Saldo;
    }

    public int getNoKartu() {
        return this.NoKartu;
    }

    public void setNoKartu(int NoKartu) {
        this.NoKartu = NoKartu;
    }

    public int getPIN() {
        return this.PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public String getTglDaftar() {
        return this.TglDaftar;
    }

    public void setTglDaftar(String TglDaftar) {
        this.TglDaftar = TglDaftar;
    }

    public void TransferSaldo(long Transfer){
        this.Saldo = this.Saldo - Transfer;
    }

    public void PenambahanSaldo(long Transfer){
        this.Saldo = this.Saldo + Transfer;
    }

    
    @Override
    public String toString(){
        return ""+
        "" + getNoRek() + "'" +
        "\t" + getNamaNsb() + "'" +
        "\t\t" + getSaldo() + "'" +
        "\t" + getNoKartu() + "'" +
        "\t" + getPIN() + "'" +
        "\t" + getTglDaftar() + "'" +
        "";
    }
}

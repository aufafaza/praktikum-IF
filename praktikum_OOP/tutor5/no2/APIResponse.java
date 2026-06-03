public class APIResponse<U> {
    // Berisi public class APIResponse<U> dengan spesifikasi:
    //
    // Memiliki atribut private int statusCode, String message, dan U data.
    // Memiliki constructor untuk menginisialisasi ketiga atribut tersebut.
    // Memiliki method void printResponse() yang mencetak dengan format:
    // Response [statusCode] - [message] | Data: [data] (Type: [Nama Class dari U])
    private int statusCode;
    private String message;
    private U data;

    public APIResponse(int code, String mes, U d) {
        this.statusCode = code;
        this.message = mes;
        this.data = d;
    }

    public void printResponse() {
        System.out.println(
                "Response " + this.statusCode + " - " + this.message + " | " + "Data: " + this.data + " (Type: "
                        + data.getClass().getSimpleName()
                        + ")");
    }

}

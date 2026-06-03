public class APIRequest<T> {
    private String endpoint;
    private T payload;

    public APIRequest(String end, T pay) {
        this.endpoint = end;
        this.payload = pay;
    }

    // Memiliki Generic Method bernama execute. Method ini menerima tipe generic
    // baru <U> dengan parameter: int statusCode, String message, dan U
    // responseData.
    // Method execute ini harus melakukan dua hal:
    // Mencetak log ke layar: Executing Request to [endpoint] with payload:
    // [payload]
    // Mengembalikan sebuah objek APIResponse<U> yang baru dibuat berdasarkan
    // parameter yang diterimanya.
    public <U> APIResponse<U> execute(int statusCode, String message, U responseData) {
        System.out.println("Executing Request to " + this.endpoint + " with payload: " + this.payload);
        APIResponse<U> response = new APIResponse<>(statusCode, message, responseData);
        return response;
    }
}

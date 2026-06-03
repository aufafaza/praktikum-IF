import java.util.ArrayList;

public class Laci<T>{
    public String label;  
    public ArrayList<T> items; 
    public int cap; 

    public Laci(String label){ 
        this.label = label; 
        this.items = new ArrayList<>();
        this.cap = 10; 
    }
    public boolean simpan (T item){ 
        if (items.size() == cap){
            return false;
        }
            items.add(item); 
            return true;
        
    } 
    
    public T ambil(int i){ 
        if (i <= items.size()){
        return items.get(i-1); 
        }else{
            return null;
        }
    }

    public void set(int i, T item){ 
        if (i > 10) return; 
        else { 
            items.set(i-1, item); 
        }
    }

    public int ukuran(){ 
        return items.size(); 
    }

    public String getLabel(){ 
        return this.label; 
    }

    public String toString(){ 
        StringBuilder result = new StringBuilder();
        result.append("Laci[" + this.label + "]: [");
        for (int i = 0; i < items.size(); i++){ 
            if (i != items.size() - 1){
                result.append(items.get(i).toString());
                result.append(", ");
            }else { 
                result.append(items.get(i).toString());
            }
        }
        result.append("]");
        return result.toString();
    }
}
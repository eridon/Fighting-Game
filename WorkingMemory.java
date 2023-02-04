
public class WorkingMemory {
	
    public static String strikeA; // previous, previous strike (data)
    public static String strikeB; // previous strike (data)
    public static String strikeC;  // next, predicted, strike (assertion)
    
    public WorkingMemory() {
    	
    	strikeA = "sUnknown";
    	strikeB = "sUnknown";
    	strikeC = "sUnknown";
    }

    public static void setStrikeA (String A)
    {
    	strikeA = A;
    }
    
    public static void setStrikeB (String B)
    {
    	strikeB = B;
    }
    
    public static void setStrikeC (String C)
    {
    	strikeC = C;
    }
    
    public static String getStrikeA ()
    {
    	return strikeA;
    }
    
    public static String getStrikeB ()
    {
    	return strikeB;
    }
    
    public static String getStrikeC ()
    {
    	return strikeC;
    }

}
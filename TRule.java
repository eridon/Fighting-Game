
public class TRule {
   
    boolean          matched;
    int              weight;
   
    String          antecedentA;
    String          antecedentB;
    String          consequentC;
 
  public TRule() {
	  matched = false;
	  weight = 0;
  }
  
  public void SetRule(String A, String B, String C) {
	  
	  antecedentA = A;
	  antecedentB = B;
	  consequentC = C;
        
  }
}

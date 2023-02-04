
public class StrikePrediction {
    
	 public static final int NUM_RULES  = 27;
	 private TRule   Rules[] = new TRule[NUM_RULES];
     int             PreviousRuleFired;

     String          Prediction = "";
     //String        RandomPrediction;

     int             N;
     int             NSuccess;
     //int           NRandomSuccess;
     
     public void Initialize()
     {
    	 for (int i = 0; i<NUM_RULES; i++){
    		 TRule Rule = new TRule();
        	 Rules[i]=Rule;
    	 }   	 
    	     Rules[0].SetRule("Punch", "Punch", "Punch");
             Rules[1].SetRule("Punch", "Punch", "LowKick");
             Rules[2].SetRule("Punch", "Punch", "HighKick");
             Rules[3].SetRule("Punch", "LowKick", "Punch");
             Rules[4].SetRule("Punch", "LowKick", "LowKick");
             Rules[5].SetRule("Punch", "LowKick", "HighKick");
             Rules[6].SetRule("Punch", "HighKick", "Punch");
             Rules[7].SetRule("Punch", "HighKick", "LowKick");
             Rules[8].SetRule("Punch", "HighKick", "HighKick");
             Rules[9].SetRule("LowKick", "Punch", "Punch");
             Rules[10].SetRule("LowKick", "Punch", "LowKick");
             Rules[11].SetRule("LowKick", "Punch", "HighKick");
             Rules[12].SetRule("LowKick", "LowKick", "Punch");
             Rules[13].SetRule("LowKick", "LowKick", "LowKick");
             Rules[14].SetRule("LowKick", "LowKick", "HighKick");
             Rules[15].SetRule("LowKick", "HighKick", "Punch");
             Rules[16].SetRule("LowKick", "HighKick", "LowKick");
             Rules[17].SetRule("LowKick", "HighKick", "HighKick");
             Rules[18].SetRule("HighKick", "Punch", "Punch");
             Rules[19].SetRule("HighKick", "Punch", "LowKick");
             Rules[20].SetRule("HighKick", "Punch", "HighKick");
             Rules[21].SetRule("HighKick", "LowKick", "Punch");
             Rules[22].SetRule("HighKick", "LowKick", "LowKick");
             Rules[23].SetRule("HighKick", "LowKick", "HighKick");
             Rules[24].SetRule("HighKick", "HighKick", "Punch");
             Rules[25].SetRule("HighKick", "HighKick", "LowKick");
             Rules[26].SetRule("HighKick", "HighKick", "HighKick");

             WorkingMemory.setStrikeA("sUnknown");
             WorkingMemory.setStrikeB("sUnknown");
             WorkingMemory.setStrikeC("sUnknown");
             PreviousRuleFired = -1;

             N = 0;
             NSuccess = 0;
             //NRandomSuccess = 0;
           

     }
     
     public String ProcessMove(String move)
     {
             int     i;
             int     RuleToFire = -1;

             if((WorkingMemory.getStrikeA()).equalsIgnoreCase("sUnknown"))
             {
                     WorkingMemory.setStrikeA(move);
                     Prediction = "sUnknown";
                     
                     return "sUnknown";
             }

             if((WorkingMemory.getStrikeB()).equalsIgnoreCase ("sUnknown"))
             {
                     WorkingMemory.setStrikeB(move);
                     Prediction = "sUnknown";
                   
                     return "sUnknown";
             }
             

             // total and adjust weights...
             N++;
            
             System.out.println("working Memory for strike A is: " + WorkingMemory.getStrikeA() + "; N is: " + N);
             System.out.println("working Memory for strike B is: " + WorkingMemory.getStrikeB() + "; N is: " + N);
             System.out.println("current move is: " + move + "; N is: " + N);
             
             
             // make prediction using forward chaining ...
             for(i=0; i<NUM_RULES; i++)
             {
                     if(Rules[i].antecedentA.equalsIgnoreCase(WorkingMemory.getStrikeA()) &&
                        Rules[i].antecedentB.equalsIgnoreCase(WorkingMemory.getStrikeB()))
                             Rules[i].matched = true;
                     else
                             Rules[i].matched = false;
             }

             // pick the matched rule with the highest weight...
             RuleToFire = -1;
             for(i=0; i<NUM_RULES; i++)
             {
                     if(Rules[i].matched)
                     {
                             if(RuleToFire == -1)
                                     RuleToFire = i;
                             else if(Rules[i].weight > Rules[RuleToFire].weight)
                                     RuleToFire = i;
                     }
             }

             // fire the rule
             if(RuleToFire != -1) {
                     WorkingMemory.setStrikeC(Rules[RuleToFire].consequentC);
                     PreviousRuleFired = RuleToFire;
                     Prediction = WorkingMemory.getStrikeC();
                     System.out.println("prediction is : " + Prediction);
             } else {
                     WorkingMemory.setStrikeC("sUnknown");
                     PreviousRuleFired = -1;
                     System.out.println("prediction is : " + Prediction);
             }
             
             
             if(move.equals(Prediction))
             {
                     NSuccess++;
                     if(PreviousRuleFired != -1)
                             Rules[PreviousRuleFired].weight++;
             } else {
                     if(PreviousRuleFired != -1)
                             Rules[PreviousRuleFired].weight--;

                     // increment the weight of the rule that should have been fired
                     for(i=0; i<NUM_RULES; i++)
                     {
                             if(Rules[i].matched && (Rules[i].consequentC.equals(move)))
                             {
                                     Rules[i].weight++;
                                     break;
                             }
                     }
                     
             }
             
             //checking weights
             for (int j=0; j<NUM_RULES; j++)
        	 { System.out.print(" weight for rule " + j + " is: " + Rules[j].weight);
        	   
        	   if (j%2 == 0)
        		   System.out.println();
        	 }
             
             /*if(move.equals(RandomPrediction))
                     NRandomSuccess++;*/

             //checking matched status       
             for (int j=0; j<NUM_RULES; j++)
        	 { System.out.print(" matched for rule " + j + " is: " + Rules[j].matched);
        	   
        	   if (j%2 == 0)
        		   System.out.println();
        	 }
             
             // roll back...
              WorkingMemory.setStrikeA(WorkingMemory.getStrikeB());
              WorkingMemory.setStrikeB(move);
              
             return WorkingMemory.getStrikeC();
             
     }
     
}
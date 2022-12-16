

public class Calculator {
////////////////////////////////////////////////////////////////////////Question 1B- PostFixcalculator.
	public int evaluatePostFix( String s) throws InvalidPostfixException{
		if(!checkvalid(s)) {///////////if expression is not valid it will throw exception.
			throw new InvalidPostfixException ("Exception");
		}
		
		MyStack st1 = new MyStack();
         int i=0;
		char x= s.charAt(s.length()-1);
		int x1= x;
		while(i<s.length()) {
			char c= s.charAt(i);
			if( Character.isDigit(c)) {
				if(i<s.length()-1 && (x1<48 || x1>57) ){  /* to check if there is more than one digit number 
				and this block only execute when last character is not any digit, that is case is handled in other block*/
					char c2 = s.charAt(i+1);
		 		    if(Character.isDigit(c2)) {
		 		    	int k= i+1;
		 		     while(!(s.charAt(k)==' ')&& k<s.length()) {
		 		    	k++;
		 		    	}
		 		     String s1= s.substring(i, k);
		 		     int j= Integer.parseInt(s1);
		 		     st1.push(j);
		 		    i=k;                                               
					
				}else {   // in case there is no more than one digit number then push the one character.
					int j1 =Integer.parseInt(String.valueOf(c));
					st1.push(j1);
					i++;
				}
				}else {   //this case is for, if whole string is only a numeric value
					 String s1= s.substring(i, s.length());
		 		     int j= Integer.parseInt(s1);
		 		     st1.push(j);
		 		    i= s.length();
					
				}
			
			
			}else if(c==' ') {///////////// if there is space, nothing to do
				i++;
				continue;
			}else {
				int n1=0;
				try {
					n1 = (int) st1.pop();
				} catch (EmptyStackException e) {
					
					e.printStackTrace();
				}
	            int n2=0;
				try {
					n2 = (int) st1.pop();
				} catch (EmptyStackException e) {
					
					e.printStackTrace();
				}
	             if(c=='*'){
	                int n3= n2*n1;
	                
	                st1.push(n3);
	                i++;
	            }
	            if(c=='+'){
	                int n3 = n2+n1;
	                st1.push(n3);
	                i++;
	            }
	            if(c=='-'){
	                int n3 = n2-n1;
	                   st1.push(n3);
	                   i++;
	            }
			}
		}
		int result = 0;
		try {
			result = (int) st1.top();
		} catch (EmptyStackException e) {
			
			e.printStackTrace();
		}
		return result;
		}
	////////////////////////////////////////////////////function to check a valid PostFixexpression.
		public boolean checkvalid(String s) {
		s= s.trim();/////////////////to get rid of all trailing spaces.
		boolean is = true; //value to be return
		char c1 = s.charAt(0);
		char c2 = s.charAt(s.length()-1);
		int j=c2;
		if((c1=='+'||c1=='-'||c1=='*')) { //in case first non space character is not numeric then it will be invalid exp. 
			is = false;
			return is;
			}
		if(c2<=57&&c2>=48) {/*to make sure is whole string contain only a numeric value.
		if not and last non space character is a digit then it is invalid exp*/
			for(int i=0; i<s.length();i++) {
				char character = s.charAt(i);
				if((character==' '||character=='+'||character=='-'||character=='*')) {
					is = false;
					
				}
			}
			return is;
		}
		int noO=0;///////////storing no of operators.
		int noS=0;///////// storing no of spaces.
		int noN=0;///////// storing no of numeric value.
		for(int i=0; i<s.length(); i++) {
			char c= s.charAt(i);
			if(c=='+'||c=='-'||c=='*') {
				noO++;
			}else if(c==' ') {
				noS++;
			}
			
			if( Character.isDigit(c)) {
				if(i<s.length()-1) {
				char c11= s.charAt(i+1);
				if(Character.isDigit(c11)) {
					continue;
				   }
				else if((c11=='+'||c11=='-'||c11=='*')) {/*checking if character just after a numeric value is any operator in this
				                                             case exp is invalid */
					is = false;
					return is;
					}
				else if(c11==' ') {  
					noN++;
				}
				else {
					if(i>=1 ) {
						char c12 = s.charAt(i-1);
						if(c12=='+'||c12=='-'||c12=='*') {/*checking if character just before a numeric value is any operator in this
                                                         case exp is invalid */
							is = false;
							return is;
						}
                 }
				}
			}
			
		}
		}
		if(noS<2*noO) {     //in any case no of spaces should be greater than or equal to twice the no of operators for exp to be valid.  
			is = false;
			
		}else if(noN<= noO) {//////////////if no of numeric values is less than or equal to no of operator then exp is invalid.
			is = false;
		}
		return is;
	}
		/////////////////////////////////////Exception class for InvalidPostfixException.
	//  class  InvalidPostfixException  extends Exception{
	// 	 InvalidPostfixException(String s){
	// 		super(s);
	// 	}
		
	// }
//////////////////////////////////////////////////////////Question 1C- Implementing a calculator.
	////////////////////////Function to convert infix to postFix.
	public String convertExpression(String exp) throws InvalidExprException 
	{
		String output ="";
		if(!checkvalidexp(exp)) {/////////////////in case exp is not valid it throws exception
			throw new InvalidExprException("Exception");
		}
		MyStack st = new MyStack();
        int i=0;
		char x= exp.charAt(exp.length()-1);
		int x1= x;
		
		while(i<exp.length()){
		    char c= exp.charAt(i);
		    int j= c;
		   if((j>=48 && j<=57)){
		    	 if(i<exp.length()-1  ) {//// to make sure if there is more than one digit number or not.
                    char c2 = exp.charAt(i+1);
		 		    if(Character.isDigit(c2)) {
		 		    	int k= i+1;
		 		     while( k<exp.length()&&Character.isDigit(exp.charAt(k))) {
		 		    	k++;
		 		    	}
		 		    if(output.isEmpty()) {
		 		    	output = output+exp.substring(i, k);
	 		     }else {
		    		 
		 		        output= output+" "+exp.substring(i, k);;
		 		        }
		                 i=k;
	    	 }else {                ////else we will add one digit number with out space if output is empty string
	    		 if(output.isEmpty()) {
	    			 output= output+c;
	 		     }else {
		    		 output= output+" "+c;
		 		         }
	    		 i++;
	 		       }
	    	 }else {           //////this is for if there one digit number at the end.
	    		 if(output.isEmpty()) {
	    			 output= output+c;
	 		     }else {
		              output= output+" "+c;
		 		       }
	    		 i++;
		   }
		 }else if(c=='('){
		      st.push(c);
		      i++;
		    }else if(c==')'){
		        try {
					while((char)st.top()!='('){
					    try {
							output+=" "+st.pop();
						} catch (EmptyStackException e) {
							e.printStackTrace();
						}
					    }
				} catch (EmptyStackException e1) {
					e1.printStackTrace();
				}
		            try {
						st.pop();
					} catch (EmptyStackException e) {
						e.printStackTrace();
					}
		            i++;
		            
		    }else if(c==' ') {
		    	i++;
		    	
		    }else {  
		             if(c=='*'){
		            if(!st.isEmpty()){
		            char c1 = 0;
					try {
						c1 = (char) st.top();
					} catch (EmptyStackException e2) {
						e2.printStackTrace();
					}
		            while(c1=='*'){
		                char c2 = 0;
						try {
							c2 = (char) st.pop();
						} catch (EmptyStackException e1) {
							e1.printStackTrace();
						} 
		                output+=" "+c2;
		                if(!st.isEmpty()) {
			                try {
								c1 = (char) st.top();
							} catch (EmptyStackException e) {
								e.printStackTrace();
							}
			            }else {
			            	break;
			            }
		              }
		            }
		            st.push(c);
		            i++;
		            }else if(c=='+'){
		            if(!st.isEmpty()){
		            char c1 = 0;
					try {
						c1 = (char) st.top();
					} catch (EmptyStackException e2) {
						e2.printStackTrace();
					}
		            while(c1=='*'||c1=='+'||c1=='-'){
		                char c2 = 0;
						try {
							c2 = (char) st.pop();
						} catch (EmptyStackException e1) {
							e1.printStackTrace();
						} 
		                output+=" "+c2;
		                if(!st.isEmpty()) {
			                try {
								c1 = (char) st.top();
							} catch (EmptyStackException e) {
								e.printStackTrace();
							}
			            }else {
			            	break;
			            }
		           }
		            }
		            st.push(c);
		            i++;
		            }else if(c=='-'){
		           if(!st.isEmpty()){
		            char c1 = '0';
					try {
						c1 = (char) st.top();
					} catch (EmptyStackException e2) {
						e2.printStackTrace();
					}
		            while(c1=='*'||c1=='+'||c1=='-'){
		                char c2 = '0';
						try {
							c2 = (char) st.pop();
						} catch (EmptyStackException e1) {
							e1.printStackTrace();
						} 
		                output= output+" "+c2;
		                if(!st.isEmpty()) {
		                try {
							c1 = (char) st.top();
						} catch (EmptyStackException e) {
							e.printStackTrace();
						}
		            }else {
		            	break;
		            }
		            }
		           }
		            st.push(c);
		            i++;
		        }
		             }
		  }
	while(!st.isEmpty()){
		
	    char c = (char)(0);
		try {
			c = (char) st.pop();
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
	    output= output +" "+c;
	}
		
	return output;
		
	}
/////////////////////////////////////////////this function is to check whether an infix expression is valid or not.
	public boolean checkvalidexp(String s) {
		boolean is = true;
		int noO= 0;// stores no of operators
		s= s.trim();/////////////////////to get rid of trailing spaces.
		int n= s.length();
		char c1= s.charAt(0);
		char c2 = s.charAt(n-1);
		if((c1==')'||c1=='+'||c1=='-'||c1=='*')||(c2=='('||c2=='+'||c2=='-'||c2=='*')) {
			is = false;
			return is;
		}
		int nob1= 0;//stores no of '('
		int nob2= 0;// stores no of ')'
		int noN = 0;//stores no of numbers
		for(int i=0; i<s.length(); i++) {
			char c= s.charAt(i);
			if(c=='+'||c=='-'||c=='*') {
				noO++;
			}
			if(c=='(') {
				nob1++;
				char c11= s.charAt(i+1); // if there is any operator just after '(' bracket then exp is invalid
				if(c11=='+'||c11=='-'||c11=='*') {
					is= false;
					return is;
					}
				}else if(c==')') { ////// if there is any operator just before '(' bracket then exp is invalid
				nob2++;
				char c11= s.charAt(i-1);
				if(c11=='+'||c11=='-'||c11=='*') {
					is= false;
					return is;
					}
				}
			if(i<=n-1) {        ///this is to count no of numeric values 
				if(Character.isDigit(c)) {
					if(i<n-1) {
					if(Character.isDigit(s.charAt(i+1))) {
						continue;
					}else {
						noN++;
					}
					}else if(i==n-1) {
						noN++;
					}
				}
			}
			}
		if(nob1!=nob2) {
			is = false;
		}
		if(noN!= (noO+1)) {
			is = false;
		}
		return is;
	}
	////////////////////////////////////////////////////// Exception class for InvalidExprException
	//  class InvalidExprException  extends Exception{
	// 	 InvalidExprException(String s){
	// 		super(s);
	// 	}
	// 	}
}
 class InvalidExprException  extends Exception{
		 InvalidExprException(String s){
			super(s);
		}
		}
 class  InvalidPostfixException  extends Exception{
		 InvalidPostfixException(String s){
			super(s);
		}
		
	}
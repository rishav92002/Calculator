
public class MyStack implements StackInterface{
	 int capacity=1;
	Object E;
    Object [] stack = new Object[capacity];
     int top=-1;
	
	public  void push(Object p){
		if(p!=null){
		if(top==capacity-1) {
		newStack(capacity,p);
		capacity = capacity*2;
          }else {
			top++;
			stack[top]=p;
		}
		}
}
	public  void newStack(int capacity, Object i) {
		Object[] newstack = new Object[capacity*2];
		for(int j=0; j<capacity; j++) {
			newstack[j] = stack[j];
			}
		stack = newstack;
		top++;
		stack[top]=i;
		}
	public  Object pop() throws EmptyStackException{
		if(top==-1) {
			throw new EmptyStackException("Exception");
		}
		
		else {
		
			Object ret = stack[top];
			stack[top]= E;
			top--;
			return ret;
		}
		
		}
	public Object top() throws EmptyStackException{

		if(top==-1) {
			throw new EmptyStackException("Exception");
		}else {
			Object ret = stack[top];
			return ret;
		}
	}
	public boolean isEmpty() {
		return (top==-1);
	}
	public void print() {
		for(Object num: stack) {
			System.out.print(num+" ");
		}
	}
	public String toString() {
		String s= "]";
		for(int i=0; i<=top; i++) {
			if(i==top) {
				s= stack[i]+s;
			}else {
				s= ", "+stack[i]+s;
			}
		}
		s= "["+s;
		return s;
	}
	//  class EmptyStackException  extends Exception{
	// 	  EmptyStackException(String s){
	// 		super(s);
	// 	}
		
	// }
	 
}
 class EmptyStackException  extends Exception{
		  EmptyStackException(String s){
			super(s);
		}
		
	}
		
	
/**
	@author:	Brian Mason
	@date:		October 9, 2017
	@section:	CSC-120(N01)
	@desc:		This application validates credit card number
	*			based on the Luhn Check.
*/
										
import java.util.Scanner;
public class CreditCardValidatorMasonBrian{
	/**
	Main Method
	*/
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a Credit Card Number: ");
		String _card = input.nextLine();
		String _error = "Invalid Card";
		 
		isValid(_card);
		getCompany(_card);
		sumOfDoubleEvenPlace(_card);
		sumOfOddPlace(_card);
		
		System.out.println("getCompany: " + getCompany(_card));
		System.out.println("getDigit: " + getDigit(5));
		System.out.println("getDigit: " + getDigit(16));
		System.out.println("sumOfDoubleEvenPlace: " + sumOfDoubleEvenPlace(_card));
		System.out.println("sumOfOddPlace: " + sumOfOddPlace(_card));
		
		
		if (isValid(_card) == true){
			System.out.println("Credit Card Company: " + getCompany(_card));
			System.out.print("Credit Card is Valid.");
		}else{
			System.out.print("Credit Card is invalid.");
			
		}
	}
		
		/**This method returns the company of a card based on the 
		*  the number at (0) index.
		*  @param _card:  credit card entered by the user.
		*  @returns String that telling credit card company
		*/
	public static String getCompany(String _card){
			String _errorLength = "Invalid number of digits.";
			String _errorInvalid = "Invalid credit card number.";
			
			if (_card.length() >= 13 && _card.length() <=16){
				if (_card.charAt(0) == '4'){
					return "Visa";
				}else if (_card.charAt(0) == '5'){
					return "Mastercard";
				}else if (_card.charAt(0) == '6'){
					return "Discover";
				}else if (_card.charAt(0) == '3' && _card.charAt(1) == '7'){
					return "American Express";
				}else{
					return _errorInvalid;
				}
			}else {
				return _errorLength;
			}
	}

		/** Returns a single-digit int.  If int is 2 digits,
		*   method returns the sum of the two digits.
		*   @param _number:  a digit b/t 0 and 18
		*   @param _numberString:  converts number to String
		*   	for length() method.
		*   @returns int
		*/
	public static int getDigit(int _number) {
		String _numberString = _number + "";
		
		if (_numberString.length() == 1){
		    _number = Integer.parseInt(_numberString);
			return _number;
		
			
		}else if (_numberString.length() == 2) {
			 	   
			   int _number1 = Character.getNumericValue(_numberString.charAt(0));
			   int _number2 = Character.getNumericValue(_numberString.charAt(1));
			   _number =  _number1 + _number2;
		}
		return _number;
	}
		/** Doubles every 2nd digit from right to left.
		*   invokes the getDigit() method to for all
		*   two-digit numbers.
		*   @param _length:  _card length to differentiate
		*		b/t cards with even and odd number of characters.
		*	@param i:  used to increment charAt() in loop.
		*   @param _evens:  variable to convert char to int.
		*   @param _evenNumbers:  return variable for method total
		*/
	
	public static int sumOfDoubleEvenPlace(String _card){
		int _length = _card.length() ; 
		int i = 2;
		int _evens = 0;
		int _evenNumbers = 0;
		
		//to communicate with getDigit()
		getDigit(_evens);
		
		//for cards with an even number of characters
		if (_length % 2 == 0){
			_evens = Character.getNumericValue(_card.charAt(0)) * 2;
			_evens = getDigit(_evens);
			_evenNumbers = _evenNumbers + _evens;
		
			while (i < _length){
				_evens = Character.getNumericValue(_card.charAt(0 + i)) * 2;	
				_evens = getDigit(_evens);
				i = i + 2;
				_evenNumbers = _evenNumbers + _evens;
			}
		//for cards with odd number of characters.	
		}else {
			_length = _card.length() - 1;
			_evens = Character.getNumericValue(_card.charAt(1)) * 2;
			_evens = getDigit(_evens);
			_evenNumbers = _evenNumbers + _evens;
		
			while (i < _length){
				_evens = Character.getNumericValue(_card.charAt(1 + i)) * 2;
				_evens = getDigit(_evens);
				i = i + 2;
				_evenNumbers = _evenNumbers + _evens;
			}
		}
	return _evenNumbers;
	}
		/**Returns the sum of the odd-place numbers
		*   @param _length:  _card length to differentiate
		*		b/t cards with even and odd number of characters.
		*	@param i:  used to increment charAt() in loop.
		*   @param _odds:  variable to convert char to int.
		*   @param _oddNumbers:  return variable for method total
		*/
	public static int sumOfOddPlace(String _card){
		int _length = _card.length(); 
		int i = 2;
		int _odds = 0;
		int _oddNumbers = 0;
	
		//for cards with even number of characters
		if (_card.length() % 2 == 0){
			_odds = Character.getNumericValue(_card.charAt(1) ) ;
			_oddNumbers = _oddNumbers + _odds;
			
			while (i < _length){
				_odds = Character.getNumericValue(_card.charAt(1 + i));	
				i = i + 2;
				_oddNumbers = _oddNumbers + _odds;
			}
		
		//for cards with odd number of characters
		}else {	
			_odds = Character.getNumericValue(_card.charAt(0));
			_oddNumbers = _oddNumbers + _odds;
			
			while(i < _length){
				_odds = Character.getNumericValue(_card.charAt(0 + i));
				i = i + 2;
				_oddNumbers = _oddNumbers + _odds;
			}
		}
	return _oddNumbers;
	}
		/**returns true if card is valid.  invokes getCompany(), 
		*  	sumOfDoubleEvenPlace() and sumOfOddPlace(), then uses
		*	modulus to determine if their sum is divisible by 10.
		*  @param _card:  card number entered by the user.
		*  @returns boolean value.
		*/
	
	public static boolean isValid(String _card){
		//communication with sumOfDoubleEvenPlace() and sumOfOddPlace()
		sumOfDoubleEvenPlace(_card);
		sumOfOddPlace(_card);
		getCompany(_card);
		String _errorLength = "Invalid number of digits.";
		String _errorInvalid = "Invalid credit card number.";
		
		//condition for valid credit card.
		if((getCompany(_card) != _errorInvalid) && (getCompany(_card) != _errorLength)){
			if((sumOfDoubleEvenPlace(_card) + sumOfOddPlace(_card) != 0)){	//if user enters all zeroes
				if ((sumOfDoubleEvenPlace(_card) + sumOfOddPlace(_card)) % 10 == 0 ){
					return true;
				
				}else{
					return false;
				
				}
			}else{
				return false;
			
			}
		}else{
			return false;
		}	
	}
}
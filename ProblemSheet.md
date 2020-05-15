# Review

## The special value `null`
This week, we learned about `null`, a kind of value in Java, which can be used anywhere a reference of any type is expected, and indicates that a reference does not exist. `null`s can be used in _any_ reference position:

```java
class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

interface StrToStr {
	String apply(String s);
}

class LengthAsStr implements StrToStr {
	public String apply(String s) {
		return "" + s.length();
	}
}

class ExampleNulls {
	String noStr = null;
	Point noPoint = null;
	StrToStr noInterface = null;
	LengthAsStr noClass = null;
	int[] noArray = null;
}

```

It can also be passed an argument, return from a method, etc. However, keep in mind that since `null` represents the lack of a reference, trying to use a `null` by calling its methods, accessing its fields, or updating its elements will cause a `NullPointerException` error:

```java
// ...

class ExampleNulls {
	String noStr = null;
	int[] noArray = null;
	Point noPoint = null;
	
	// Each of the following will cause a NullPointerException
	int errorLength = noStr.length();
	int errorLengthTwo = noArray.length;
	int errorElement = noArray[0];
	int errorX = noPoint.x;

	{
		noArray[0] = 1;
		noPoint.y = 2;
	}
}
```

So, if at any point in your code, you cannot be certain that a reference cannot be `null`, one way to check is simply to compare it:

```java
/*
	This method returns the length of the
	given String, or -1 if the String is
	null.
*/
int safeLength(String s) {
	int length;

	if (s != null) {
		length = s.length();
	} else {
		length = -1;
	}
	
	return length;
}
```

## Array update expression
This week we also saw how to change the elements of an array. While the length of any array is fixed once we create it, Java allows us to change the value of any of its elements using a similar syntax to how we can access them: 

```java
/* array name */[/* element index */] = /* new value */
```

For example, to increment each element in an array of integers by `1`, we can write:

```java
void addOne(int[] arr) {
	for (int i = 0; i < arr.length; i += 1) {
		arr[i] = arr[i] + 1;
	}
}
```

Notice how in the line `arr[i] = arr[i] + 1`, we access the same element in the array twice. Once to read it's value and add `1` to it: `arr[i] + 1`, and another time to update that element in the array: `arr[i] = /* ... */`

## Sized array creation
Another feature of Java that we revisited this week was how to create arrays of a particular size, without hard-coding the size, or writing each value in the array manually using the `{ ... }` notation. To do so, we saw that we can use the `new` keyword before the type of the array, and giving the length of the array between the square-brackets `[]`:

```java
int[] arrayOfSizeTen = new int[10];
```

When we use this sized array creation, Java automatically fills the array using the _default value_ of the array's element type. For instance, in the code above, `arrayOfSizeTen` will contain ten `0`s, because the default value for `int` is `0`. The default values are:

|   Type   |  Value |
|----------|--------|
|`int`     | `0`	|
|`double`  | `0.0`	|
|`boolean` | `false`|
| Referece | `null` |

where "Reference"s are any reference type (String, classes, interfaces, etc.)

The length value used in sized array creation can be any `int` expression, so we can create arrays of different lengths based on another value:

```java
/*
	We can call this method with arrays of different
	lengths, and it will always return a new array
	of that length!
*/
int[] copyArray(int[] arr) {
	int[] returnValue = new int[arr.length];
	for (int i = 0; i < arr.length; i += 1) {
		returnValue[i] = arr[i];
	}
	return returnValue;
}
```

## Lambdas

***Note***: This is a bonus topic, which we haven't seen in lecture. However, it _could_ be useful for PA7, so let's briefly review it here:

---

The "Lambda Expression" feature in Java allows us to write (1) an `interface` and (2) a `new` in a single line of code. Suppose that we have defined an interface `StrToStr` with exactly one method `apply`:

```java
interface StrToStr {
	String apply(String s);
}
```

Let's say that we want to use this interface to have a method that returns a String representing the length of the parameter. We have already seen that we can do so by writing a class that `implement`s this interface:

```java
interface StrToStr {
	String apply(String s);
}

class LengthAsStr implements StrToStr {
	public String apply(String s) {
		return "" + s.length();
	}
}

class LambdaExamples {
	StrToStr length = new LengthAsStr();
	String lengthOfCse = length.apply("CSE");
}
```

Notice how, even though this is 4 lines of code, there are only really two pieces of new information in this implementation:

1. That we can assign `new LengthAsStr()` to anything that accepts a `StrToStr` type.
2. Calling `apply(s)` on a `LengthAsStr` object returns this value: `"" + s.length()`.

The rest of the information, such that LengthAsStr containing a method named `apply` that accepts a single `String` parameter and returns `String`, was already implicit in the fact that `LengthAsStr` implements the `StrToStr` interface. 

Java allows us to shorten this code to a single line, by only providing the _definition_ of the `apply` method where an instance of `StrToStr` is expected:

```java
interface StrToStr {
	String apply(String s);
}

// We don't need the LengthAsStr class anymore!

class LambdaExamples {
	StrToStr length = s -> "" + s.length();
	String lengthOfCse = length.apply("CSE");
}
```

This code performs the same operation as the one above, but without requiring us to write the `LengthAsStr` class, instead by only providing an implementation of the `apply` method. Notice that because we are assigning this lambda expression to a field of type `StrToStr`, Java knows that the lambda is meant to be the `apply` method, taking one parameter of type `String`, and returning a `String`. So it reads the expression before the `->` as the name of the parameter, and the expression after the `->` as the body of the `apply` method, and creates an object implementing `StrToStr` for us in a single line!

To summarize, the syntax of a lambda expression is as follows:

```
/* parameter */ -> /* return expression without the return keyword */
```

and it can be used anywhere an interface, containing exactly one method, is expected. There, it both defines a class implementing the interface _and_ creates an object of that class, with the lambda expression as the implementation of the interface's method.

# Problems

## Problem 1
In a file named `Bat.java`, Write a Java program named `Bat` that accepts a single argument, the path to a text file, and prints each line in that file.

For example, if we have a file named "twinkle.txt" in the same directory as the `Bat.class` file, containing the following text:

```
TWINKLE, twinkle, little star,
How I wonder what you are!
Up above the world so high,
Like a diamond in the sky.
```

The command `java Bat "twinkle.txt"` should print:

```log
$ java Bat "twinkle.txt"
TWINKLE, twinkle, little star,
How I wonder what you are!
Up above the world so high,
Like a diamond in the sky.
$ 
```

Hint: See the ["Reading Files" section of PA7](https://ucsd-cse11-s20.github.io/pa7/#reading-files) for a code snippet that reads the contents of a file.

## Problem 2
In a file named `Rotate.java`, write a Java program `Rotate` that accepts two parameters, an integer `n`, and a list of strings separated by `,`, and rotates that list to the left by `n` before printing each element on a new line:

```log
$ java Rotate 0 a,b,c
a
b
c
$ java Rotate 1 a,b,c
b
c
a
$ java Rotate 2 a,b,c
c
a
b
$ java Rotate 3 a,b,c
a
b
c
```

You can assume that `n` is always between 0 and the length of the array 

Hint: You might find the [String `split`](https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/lang/String.html#split(java.lang.String)) method useful for this.

## Problem 3
In a file named `Range.java`:

1. write the `range` method that we saw in lecture:

```java
import tester.*;

class Range {
	int[] range(int start, int stop) {
		int size = stop - start;
		int [] nums = new int[size];	
		for (int index = 0; index < size; index += 1) {
			nums[index] = index + start;
		}
		return nums;
	}
	
	int[] range1 = range(20, 30);
	int[] range2 = range(0, 5);
}
```

2. Modify the for-loop such that instead of going from index `0` to `size`, the index goes from `start` to `stop`. You might need to modify other parts of the code for this to work.

3. Try to run the method with a `start` variable that is _larger_ than `stop`. Why did the program crash? Modify the method to return a reasonable value instead of crashing. What "reasonable" value did you pick, and why?
1)
N			Avg Time (ns)
100			    20161
1,000		    67213
10,000		  3791545
100,000		 49588753
1,000,000	109549282
--------------------------------------------------------------
What are reasonable values of "small" and "large" values of N?
--------------------------------------------------------------
This is a pretty ambiguous question but I'll take a stab at it.
A reasonable small value of N would be 10,000 I would say, still
extremely fast and pretty practical for most data-sets. If you're
working with very large databases then 100,000 would probably be 
the limit. 50ms is  about what you want to have to take with an 
algorithm in real-world applications and making clients wait any 
more than that is not ideal.

2)
MinSort construction
N			Time (ns)
100		  	    64395
1,000		   537679
10,000		  1613830
100,000		  6731468
1,000,000	 29146911

MinSort removal
N			Time (ns)
100		  	   299853
1,000		  2728696
10,000		  6059467
100,000		 22808925 
1,000,000	213880437

Time Spent
N			Construct	Removal
100			17.68%		82.32%
1,000		16.46%		83.54%
10,000		21.03%		78.97%
100,000		22.79%		77.21%
1,000,000	11.99%		88.01%
AVERAGE		17.99%		82.01%
--------------------------------------------------------
Do these agree with the expected rate of runtime growth?
--------------------------------------------------------
Yes, I would say both construction and removal are linear.
Each time I multiplied N by ten, I get a proportional 
increase in the runtime. In fact, it almost looks like 
the runtime is on average pretty close to 5N.

HeapSort construction
N			Time (ns)
100		  	   36346
1,000		  208197
10,000		  721384
100,000		 3570574 
1,000,000	22802604

HeapSort repair
N			Time (ns)
100		  	   204642
1,000		   690965
10,000		  7195270
100,000		 25071447
1,000,000	259800114

Time Spent
N			Construction	Repair
100			15.08%			84.92%
1,000		23.15%			76.85%
10,000		9.11%			90.89%
100,000		12.47%			87.53%
1,000,000	8.07%			91.53%
AVERAGE		13.58%			86.34%

--------------------------------------------------------
Do these agree with the expected rate of runtime growth?
--------------------------------------------------------
Yes, I would say they're both linear. Pretty much the same
case as with the MinPQ implementation (see above).

As you can see, I wasn't able to use the sizes specified in
the directions. I was getting an OutOfMemory error because
the Integer array was just too big. As you can see in my code
I tried using a Byte array instead, and it almost worked but
the memory usage of having to create another array to autobox
it (I couldn't find another way) was also too much. So I resorted
to smaller values of N. I hope everyone else had this problem too.
I know Connor did too, and he's been trying to find a solution but
it doesn't look like he's been successful. We think it has to do
with Windows limiting the memory usage for processes (the JVM does
this too but you can expand it with a JVM argument).

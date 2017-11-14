# Introduction On K-Induction {#introduction-on-k-induction .Appendix}

The AGREE tool framework uses *induction* to try to prove the system
level-guarantees from the component-level guarantees. But what does this
mean? To explain, we first refresh the user’s understanding of
mathematical induction performed over natural numbers. Often, one wishes
to prove a mathematical fact of the following sort:

$$\sum_{x = 1}^{n}x = \frac{\left( n + 1 \right)*n}{2}$$

We can prove this by *weak* *induction.* This involves two steps: first,
a base case, where we show that the property holds for the initial value
(in this case, the value 1), and an inductive case, where if we assume
the property is true of *n*, we prove that it is true of *n+1.* For this
example, the base case is

$$\sum_{x = 1}^{1}x = \frac{\left( 1 + 1 \right)*1}{2}$$

Since $1 = \frac{2}{2}$ , we satisfy the base case. If we assume that
the property is true of *n,* we can prove the inductive case over (n+1)
as follows:

$$\sum_{x = 1}^{(n + 1)}x = \frac{\left( (n + 1) + 1 \right)*(n + 1)}{2}$$

= def. of summation

$$\sum_{x = 1}^{(n)}x + \left( n + 1 \right) = \frac{\left( (n + 1) + 1 \right)*(n + 1)}{2}$$

= induction hypothesis

$$\frac{\left( n + 1 \right)*n}{2} + \left( n + 1 \right) = \frac{\left( (n + 1) + 1 \right)*(n + 1)}{2}$$

= arithmetic expansion

$$\frac{n^{2} + n}{2} + \ \frac{2n + 2}{2} = \frac{n^{2} + 3n + 2}{2}$$

= arithmetic equalities

$$\frac{n^{2} + 3n + 2}{2} = \frac{n^{2} + 3n + 2}{2}$$

QED.

The induction principle used by AGREE is similar. However, instead of
performing induction over natural numbers, it performs induction over
the *transition system* that defines the properties. Through a
compilation step, any AGREE model can be turned into a complex
first-order logical formula that defines how the system can evolve from
one time instant to the next time instant, denoted *T*; the formula T is
defined over a set of *pre-state* variables and a set of *post-state*
variables, that describe the values of the variables in the model in the
state before and after the transition. This idea is not entirely
straightforward, but a full explanation is outside the scope of this
User’s Guide. For a complete explanation please see *Model Checking* by
Ed Clarke et. al or *Logic in Computer Science* by Huth and Ryan.

Using this notation, and a formula *I* that defines the set of allowed
initial values for variables, you can describe the evolution of the
system as follows:

$$I\left( s_{0} \right)\ \&\ T\left( s_{0},\ s_{1} \right)\ \&\ T\left( s_{1},\ s_{2} \right)\&\ T\left( s_{2},s_{3} \right)\&\ldots$$

Where I defines the initial constraint on the variables and the T’s
define the step-to-step evaluation of the system. This provides a
structure from which you can perform induction. Suppose you define a
property that you want to hold over a system state as *P(s).* Then it is
possible to talk about performing induction over this structure.


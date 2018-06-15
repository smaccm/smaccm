---
title: Resolute Reference Guide
author:
- Peter Feiler
- Julien Delange
autoSectionLabels: true
linkReferences: true
cref: false
figPrefix:
- Figure
- Figures
tblPrefix:
- Table
- Tables
secPrefix:
- Section
- Sections
---

[]{#introduction}

Introduction
============

Resolute claim functions, computational functions, and global constants
are defined in Resolute annex libraries, *i.e.*, Resolute annex clauses
placed directly in an AADL package.

~~~ {caption="Resolute Claim Functions"}
package BudgetCase
public

annex Resolute {**
    MaximumWeight : real = 1.2kg

    SCSReq1(self : component) <=
    ** "R1" SCS shall be no heavier than " MaximumWeight%kg **
    SCSReq1VA1(self, MaximumWeight) or SCSReq1VA2(self, MaximumWeight)

    AddBudgets(self : component) : real =
    sum([WeightBudget(t) for (t: subcomponents(self))])
~~~

A claim function can be associated with component 
implementations by `prove` statements declared in a Resolute annex
subclause. The example shows the `prove` statement for a claim
function `SCSReq1` with the component itself passed in as a parameter.

~~~ {caption="Prove Statement for a Claim Function"}
system implementation SCS.Phys
    subcomponents
        sensor1: device sensor;
        sensor2: device sensor;
        actuator: device actuator;
    annex Resolute {**
        prove (SCSReq1(this))
~~~

Invoke the *Resolute* command on a component implementation. This
results in an instantiation of the component implementation and the
application of all claim functions associated with all of the components
in the instance model via the `prove` statements.

![Instantiation from OSATE menu](images/PackagePublic.png)

The verification results are then displayed in a view labeled *Assurance
Case.*

![Assurance Case Tree View](images/AssuranceCaseView.png)

[]{#claim-functions}

Claim Functions
===============

The syntax of a claim function is as follows:

~~~ {.bnf caption="Claim Function Syntax"}
<Claim_Function> ::=

    <name> "(" (<parameter> ("," <parameter) )* )? ")" "<="
    "**" <description> "**" <claim_function_expression>

<parameter> ::= <name> ":" <type>

<description> ::= <text> ( <parameter_reference> | <text>)*
~~~

The parameter declaration consists of a name and a type (see Resolute
Type System).

The description for a claim function consists of a sequence of strings
and references to claim function parameters, global constants, or local
constants (defined by `let` statements). If your values are numerical
with a unit, you can specify the unit to be used for display by
indicating the desired unit after a `%`, *e.g.*, WeightLimit%kg. The
units are those defined by Units property types in property sets and do
not have to be qualified by the Units type.

The claim function expression is assumed to be a logical expression
(`and`,  `or`, `andthen`,  `orelse`, implies (`=>`)) or quantified expressions (`forall`,`exists`) to
represent a predicate. You can also and `let` expressions to compute local variables.

In the case of `and`,  `or` and `forall`, all
expression elements are executed and then the result is evaluated and
returns true only if all claim functions return true. Executing all
claim functions allows Resolute to inform the user of all failing claim
functions rather than not executing the remaining
claim function calls after a failing claim in the case of `and` and `forall` and after a successful claim in the case of `or`.

`andthen` conditionally executes the second operand if the first one is true, i.e., the first operand acts as a precondition to the second one. This is useful when checking that a property exists (`has_property`) before retrieving it (`property`) as `property` throws an exception when the value is not found. 

`orelse` conditionally executes the second operand as an alternative if the first one is false. For example, this is useful when a predicate is evaluated in a compute function and if it returns false we want to provide an explanatory message using `fail`. 

`exists` will execute elements in the provided collection until one evaluates to true. Note that provided collections are not always in the order you expect, e.g., `subcomponents(c)` returns a collection that is ordered by component name, not the declaration order.

`=>` only executes the second operand if the first one is true. Note, however that if the left operand is false the result of `=>` is true without execute the right operand. 

A Resolute annex library is declared directly in a package through the
`annex` Resolute `{**` *\<library content\>* `**};`
statement. Typically you want to place a Resolute library in a package by itself - separate from component declarations. 

Resolute assumes that there is a global name space for the names of
claim functions and computational functions. Therefore, their names must
be globally unique. Claim and computational functions can be referenced in `prove` statements,
other claim functions, or computational functions without qualification
by a package name.


> You may have two claim functions with the same name in different packages. Resolute will not complain and will use the first one it encounters when resolving references.


[]{#application-of-claim-functions}

Application of Claim Functions
------------------------------

Claim functions are invoked on a component by specifying a `prove`
statement in a Resolute annex subclause of the component implementation.
This claim function is then executed on every instance of this component
implementation when the *Resolute* command is invoked.

A component implementation can contain multiple `prove` statements.

~~~ {.bnf caption="Multiple Prove Statements"}
<Resolute_subclause> :== "annex" "resolute" "{**" <prove_statement> ( <prove_statement> )* "**}" ";"

<prove_statement> ::= "prove" "(" <claim_function_invocation> ")"

<claim_function_invocation> ::= <claim_function_name> "("
    ( <parameter_value> ("," <parameter_value>)* )? ")"
~~~

The parameter values can be integers, real numbers, strings, Boolean
literals, model elements, references to global constants, and
collections of these types. They must match the specified type for the
parameter.

One special model element reference is expressed by the keyword
`this`. It refers to the instance model object of the model element
that contains the `prove` statement. This is the root object of an instance model as Resolute create a separate instance model every time it encounters a prove statement.

~~~
prove ( Memory_safe ( this ))
~~~

You can also identify a subcomponent of `this`; *i.e.*, associate the
verification action with a component down a path of the architecture
hierarchy. This allows you to specify a verification action for a
specific component instance. This example shows how a `prove`
statement is applied to a subcomponent called subsystem1:

~~~
prove (Fully_Connected ( this.subsystem1 ))
~~~

> The keyword `this` can only be
used in the `prove` statement --- not in claim functions or compute functions. Inside claim or compute function you can access elements of the instance model through set constructors such as `component` or `thread`.


The `prove` statement can be associated with the component classifier
of the subcomponent. In that case, it applies to all instances of that
component. We recommend that you associate `prove` statements with a
path only if the `prove` is intended for that particular instance of a
subcomponent.

[]{#uses-of-claim-functions}

Uses of Claim Functions
-----------------------

The compiler does enforce that claim functions can be invoked only in
`prove` statements, and in other claim functions, and cannot be invoked
in computational functions.

[]{#computational-functions-and-constants}

Computational Functions and Constants
=====================================

[]{#computational-functions}

Computational Functions
-----------------------

Computational functions are used to calculate a value of any type. The
result can be Boolean, numeric, model elements, or collections of items
of a specific type. Computational functions take parameters that are
typed. Computational functions have a single expression that can be
preceded by a local constant declaration.

~~~ {.bnf caption="Computational Functions"}
<computational_function> ::=
    <function_name> "(" <parameter> ("." <parameter>)* ")" ":" <return_type> "="
    <computational_expression>
~~~

-   Computational functions are defined in Resolute libraries.
-   Computational functions can be invoked in expressions of claim
    functions. Typically they are invoked in claim functions that
    represent verification actions or assumptions.

[]{#global-constants}

Global Constants
----------------

Global constants represent parameters to the verification whose value is
set once and can be used in any computational expression, including
parameters to claim function calls. Global constants can also hold the
result of a computational function or a set constructor whose value can
be determined at startup time of a Resolute command. For example, a
global constant may be used to precompute various sets of model element
instances, *e.g.*, all elements that are reachable from a component of a
certain component type.

~~~ {.bnf caption="Global Constants"}
<global_constant> ::=
    <constant_name> ":" <type> "=" <computational_expression>
~~~

-   Global constants are defined in Resolute libraries.
-   Global constants can precompute any expression.

[]{#local-constants}

Local Constants
---------------

Resolute also supports precomputation of local constants, which are used
inside a claim function or computational function. One or more local
constants can be defined before any expression. Typically, they are used
in a verification action or computational function before the logical or
computational expression. However, they can also be used before any
subexpression, *e.g.*, before the right-hand subexpression of an `and`
or `+` operator.

~~~ {.bnf caption="Local Constants"}
<local_constant> ::=
    "let" <constant_name> ":" <type> "=" <computational_expression> ";"
~~~

-   The scope of a local constant is the expression; *i.e.*, they can be
    referenced only from within the succeeding expression.
-   Local constants are used to precompute values that may be referenced
    multiple times in the succeeding expression.

[]{#predicate-expressions-and-computational-expressions}

Predicate Expressions and Computational Expressions
===================================================

A constraint expression results in a Boolean value.

[]{#predicate-expressions}

Predicate Expressions
---------------------

Predicate expressions support the following operators in increasing
precedence order:

Logical operators (the operands a, b are expressions of type Boolean):

-    a `=>` b
-   a `or` b *and* a `orelse` b
-   a `and` b *and* a `andthen` b
-   Negation: `not` a
-   Quantified logical expressions: ( `forall` | `exists` ) `(`
    \<variablename\> `:` \<collection_constructor\> `)` `.`
    \<logical_expression\>

[]{#computational-expressions}

Computational Expressions
-------------------------

Computational expressions are used in computational functions and must
return a value of the specified type. Computational expressions include
constraint expressions, arithmetic expressions, and operations on
collections of values and model elements.

Relational operators (the operands are of type `real` or `int`):

-   `<` | `<=` | `>` | `>=` | `=` | `<>`

Arithmetic operators (the operands are of type `real` or `int` and
may include a unit):

-   `+` | `-`
-   `*` | `/`
-   exponent: `^` 

-   Negation: `-` a
-   Precedence brackets: `(` a `)`

> The precedence order is Relational operators (low) to Arithmetic in increasing order. They are higher than `and` *and* `andthen`.

### Type-related operators

-   Type cast: `(` \<type\> `)` a

> Type cast is the same precedence as `not` *and* \<negations\>.

### Atomic expressions

Atomic expressions can be used as operands and have highest precedence.

-   Base type values: integer value, real value, string value, and Boolean
    value. Integer and real values can be annotated with a unit. Any unit
    defined by a Unit property type in any of the property sets is
    acceptable.

-   Global and local constant reference and parameter reference by their
    identifier

-   Computational function invocation:\
    function_name `(` (\<parameter_value\> ( `,` \<parameter_value\> )\* )? `)`

-   Conditional value: `if` condition `then` expression `else` expression

-   Qualified AADL classifier or property definition: ( \<ID\> `::` ) \*\<ID\>
    ( `.` \<ID\> )?

    +   Classifier used only as a parameter to `instance` or `instances`
        and property definition only in `property` built-in function

-   Instance model reference: `this` ( `.` \<ID\> ) \*

    +   Used only as parameter in `prove` statement


### Collection-related operators

Collections in Resolute can be either lists or sets.  Lists can contain
multiple identical elements, preserve insertion order, and support `head` and
`tail` accessors for defining recursive processing. By contrast sets can
contain no more than one element of a given value, do not necessarily support
insertion order, and support set `intersect` and `union` operators.  Both
lists and sets support quantifiers `forall` and `exists` and filtered 
collections.  Operators exist to convert a list into a set (collapsing
duplicated elements) and a set into a list.

-   Basic collection: \<Basic_list\>  | \<Basic_set\>

-   Basic list: `[` \<expression\> ( `,` \<expression\> )\* `]` | `[ ]`

-   Basic set: `{` \<expression\> ( `,` \<expression\> )\* `}` | `{ }`

-   Filtered collection: \<Filterd list\> | \<Filtered set\> 

-   Filtered list: `[` \<filtered_element\> `for` (
    `(` \<element_name\> `:` \<collection_constructor\> `)` + )
     ( `|` \<filter\_expression\> )? `]`

-   Filtered set: `{` \<filtered_element\> `for` (
    `(` \<element_name\> `:` \<collection_constructor\> `)` + )
     ( `|` \<filter\_expression\> )? `}`
    -   Note: \<filtered_element\> refers to one of the set element names
    -   Note: \<filter_expression\> is of type Boolean

~~~ {.bnf caption="Collection-Related Operators"}
<collection_constructor> ::=
    <basic_collection> | <filtered_collection> | <AADL_model_element_type> |
    <global_constant> | <local_constant> | <computational_function_invocation>
~~~

Note: The constants must have collections as their values, and the
invoked function must return a collection. Function invocations
returning a collection can be a user-defined computational function or a
built-in function (see Built-in Base Types). The *constant reference*
has to be of a collection type.

The following examples illustrate the use of collections. The first
example uses the built-in `subcomponents` function to get a collection
of subcomponents. The `forall` then iterates over the (set) collection and
executes the built-in `has_property` constraint function on each
element.

In the second example, we precompute the collection of subcomponents and
hold on to them with a local constant. We then construct a list of
real values of value 1.0 for each subcomponent that satisfies the
`has_property` constraint function, then perform the summation of the
resulting `real` (list) collection, and divide it by the size of the
subcomponent collection.

~~~ {.resolute caption="Subcomponent Weight Coverage Example"}
HasSubcomponentWeightBudget(self:component) : bool =
    forall (sub: subcomponents(self)) . has_property(sub,SEI::GrossWeight)

SubcomponentWeightBudgetCoverage(self:component) : bool =
    let subs : {component} = subcomponents(self);
    (sum([ 1.0 for (sub : subs) | has_property(sub,SEI::GrossWeight)]) / length(subs))
~~~

Collections can also be precomputed in global constants. This is useful
when you want to make use of certain collections of instance model
objects repeatedly. In this example, the global constant declaration
`MOTORS` represents the set of instances of a particular component type.

~~~
MOTORS : {component} = instances(PX4IOAR::Motor)
~~~

### The `fail` expression

The `fail` expression can be used in any computational function and
can be viewed like an exception that is thrown. It is automatically
caught by the closest enclosing claim function, interpreted as a fail of
the claim, and reported as a sub-result to the claim function. That is,
the `fail` expression is shown as a failure, and the provided text
explains the failure.

-   Exception: `fail` \<string value\> or `fail **` \<description\>
    `**` with the description syntax the same as for claim functions


[]{#resolute-type-system}

Resolute Type System
====================

~~~ {.bnf caption="Resolute Type System"}
<type> ::=
    <list_type> | <set_type> | <base_type> | <AADL_model_element_type>

<list_type> ::= "[" <type> "]

<set_type> ::= "{" <type> "}
~~~

The list and set collections allow multiple elements of the same value. In
the *SubcomponentWeightCoverage* example, the collection concept has
multiple instances of the value 1.0, and each is counted in the
summation.

[]{#built-inbase-types}

Built-in Base Types
-------------------

Base type:

-   `int`
-   `real`
-   `string`
-   `bool`
-   `range`

[]{#arithmetic-with-integers-and-reals}

Arithmetic with Integers and Reals
----------------------------------

`int` and `real` --- as well as the min and max of a `range` --- can
be values specified with a measurement unit. Any of the unit literals
defined in AADL2 Units property types are acceptable. The Units property
type definition specifies the ratios to be used to perform conversion
between the units. For `int` and `real` values with units, Resolute
converts the value to a value relative to the base unit (the first unit
defined in the Units type). All arithmetic is performed based on those
values. To present results in the description of a claim function of a
`fail` operation, the value is converted to the unit specified in the
description specification.

Resolute can retrieve property values with built-in functions. The
property values for `aadlinteger` are mapped into `int`,
`aadlreal` into `real`, and `range of` into `range`.

[]{#aadl-model-element-types}

AADL Model Element Types
------------------------

AADL model element types have an implied type hierarchy. The nesting
level indicates this type hierarchy.

-   `aadl` \[any AADL model element\]
    -   `component` \[any category of AADL component\]
        -   `abstract` \[AADL abstract component\]
        -   `bus`
        -   `data`
        -   `device`
        -   `memory`
        -   `processor`
        -   `process`
        -   `subprogram`
        -   `subprogram_group`
        -   `system`
        -   `thread`
        -   `thread_group`
        -   `virtual_bus`
        -   `virtual_processor`
    -   `connection` \[AADL connection instance\]
    -   `property` \[AADL property definition\]
    -   `feature` \[any AADL feature\]
        -   `port` \[any AADL port\]
            -   `data_port`
            -   `event_port`
            -   `event_data_port`
            -   `feature_group`
        -   `access` \[any AADL access feature\]
            -   `bus_access`
                -   `provides_bus_access`
                -   `requires_bus_access`
            -   `data_access`
                -   `provides_data_access`
                -   `requires_data_access`
            -   `subprogram_access`
                -   `provides_subprogram_access`
                -   `requires_subprorgam_access`
            -   `subprogram_group_access`
                -   `provides_subprogram_group_access`
                -   `requires_subprogram_group_access`

Resolute operates on the instance model; *i.e.*, the model elements
represent instances. Built-in collection functions operate on instance
model elements or retrieve the set of instances for a given classifier
(see Built-in Functions).

[]{#built-in-functions}

Built-in Functions
==================

[]{#built-in-functions-for-collections}

Built-in Functions for Collections
----------------------------------

`union`(\<set\>, \<set\>): set - returns a set collection
that is the union of the two inputs

`intersect`(\<set\>, \<set\>): set - returns a set collection
that is the intersection of the two inputs

`length`(\<collection\>): int - returns the size of the given set or list
collection

`size`(\<collection\>): int - returns the size of the given set or list
collection (same as length)

`member`(\<element\>, \<collection\>): Boolean - returns true if the element
is a member of the set or list collection

`sum`(\<numeric\_list\>): numeric - calculates the sum of a
list collection of integers or a list collection of real

`head`(\<list\>): type - returns the first element of the list collection

`tail`(\<list\>): list - returns all but the first element of the list collection

`append`(\<list\>, \<list\>): list - returns a list collection
that is the concatenation of the two given list collections

`as_set`(\<list\>): set - returns a set collection containing all of the
unique elements contained in the given list collection

`as_list`(\<set\>): list - returns a list collection containing all of the
elements contained in the given set collection

[]{#built-in-functions-for-ranges}

Built-in Functions for Ranges
-----------------------------

`upper_bound`(\<range\>): numeric - returns the upper bound of the range

`lower_bound`(\<range\>): numeric - returns the lower bound of the range

[]{#built-in-functions-for-properties}

Built-in Functions for Properties
-----------------------------------------

`has_property`(\<named\_element\>, \<property\>): Boolean - the named element
has the property.

`property`(\<named\_element\>, \<property\>, \<default value\>\*):
value - returns the value of the property. If a default value is
supplied, then it is returned if the element does not have the
property value. If no default is supplied and the value does not
exist, a resolute failure exception is thrown, which is caught by the closest enclosing claim function and interpreted as a fail.

`property`(\<property\>) value - 
returns the value of the property constant.

`property_member`(\<record\_property\_value\>, \<field name\>): Boolean - return the value of the record field.

> Note: There is no constructor for record values. To compare a property record value to some actual record value you have to write a function that compares each of the fields. `=` can be used to compare two property record values.

`enumerated_values`(\<property\>): [ \<string\> ] - return the an ordered set of string values.

[]{#built-in-functions-for-any-model-element-and-components}

Built-in Functions for Any Model Element and Components
------------------------------------------------------

`name`(\<named\_element\>): string - returns the name of the named element

`has_type` (named\_element): Boolean - returns true if the named
element has a classifier. The named element can be a component, feature,
or connection instance. In the case of a connection, the type of the
feature is the connection end.

`type`(\<named\_element\>): Classifier - returns the classifier of a
component, feature, or connection. In the case of a connection, the type
is that of the connection source (if not present the destination) feature. The named element must have a
type, otherwise a resolute failure exception is thrown and caught by the closest enclosing claim function.

`is_of_type`(\<named\_element\>, \<classifier\>): Boolean - true if the
named element has the classifier or one of its type extensions. The
named element must have a type. The named element can be a component,
feature, or connection instance. In the case of a connection, the type
of the feature is the connection end.

`has_parent`(\<named\_element\>): Boolean - returns true if the component
has an enclosing model element

`parent`(\<named\_element\>): named\_element - returns the parent of the
named element. The parent must exist.

`has_member`(\<component\>, \<string\>): Boolean - true if the component has
a member with the specified name (string). Members are features,
subcomponents, etc. The component can be a component instance or a
component classifier.

>  Note: Feature instances representing feature groups can have feature
    instances as members, but they are not handled by this function. See pre-declared library below for flattening feature instances in feature groups.

`is_in_array`(\<component\>): Boolean - returns true if the component instance in in an array, i.e., has an index into the array.

`has_prototypes`(\<component\>): Boolean - returns true if component classifier contains prototype declarations.

`has_modes`(\<component\>): Boolean - returns true if component directly contains mode instances.

`is_procesor`(\<component\>): Boolean - true if the component instance is
a processor

Other built-in component category tests are: `is_virtual_procesor`, `is_system`, `is_bus`, `is_virtual_bus`, `is_device`,
`is_memory`, `is_thread`, `is_process`, `is_data`, `is_subprogram`. 

> Missing tests (`abstract`, `thread_group`, `subprogram_group`) can be tested by `\<object\> instanceof \<aadl model element type\>`

[]{#built-in-functions-for-features}

Built-in Functions for Features
------------------------------

`direction`(\<feature\>): string - returns the direction of a feature
instance as string (`in`, `out`, `in out`/`in_out`). Access features do not have direction.

`is_event_port`(\<feature\>): Boolean - true if the feature instance is
an event port or event data port

`is_data_port`(\<feature\>): Boolean - true if the feature instance is
an data port or event data port

`is_port`(\<feature\>): Boolean - true if the feature instance is
a port

`is_abstract_feature`(\<feature\>): Boolean - true if the feature instance is
an abstract feature

> Note that you can test any feature or component by writing `\<object\> instanceof \<aadl model element type\>`


[]{#built-in-functions-for-connections}

Built-in Functions for Connections
---------------------------------

`source`(\<connection\>): connection\_endpoint - returns the component or
feature instance that is the source of the connection instance

`destination`(\<connection\>): connection\_endpoint - returns the
component or feature instance that is the destination of the connection
instance

`is_data_access`(\<connection\>): Boolean - true if one end of a connection is a data component

[]{#built-in-functions-for-bindings}

Built-in Functions for Bindings
-----------------------------

`is_bound_to`(\<binding_source\>, \<binding_target\>): Boolean - true if the
binding source (a component or connection instance) is bound to the binding target (a component).
It handles processor bindings, memory bindings, and connection bindings.

>   Note: The `is_bound_to` function is the same as library function `bound`.

>   Note: The `is_bound_to` function does not consider function_bindings. See *Resolute_Stdlib.aadl* for how this can be done by mirroring *processor_bound*.

[]{#built-in-functions-for-sets-and-lists}

Built-in Functions for Sets and Lists
-------------------------------------

Resolute operates on the instance model; this means that the collections
are of instance model elements.

`features`(\<named\_element\>): {feature} - returns a collection
containing the features of the named element

`subcomponents`(\<named\_element\>): {component} - returns a collection
containing the subcomponents (component instances) of the named element

`connections`(\<named\_element\>): {connection} - returns a collection of
connection instances for which the named element is an end point (source
or destination). The named element can be a component instance or a
feature instance.

`instances` (\<component\_classifier\>): {component} - returns the
collection of instances in the instance model for a given component
classifier

`instance` (\<component\_classifier\>): component - returns the component
instance for a given component classifier. The method assumes that there
is only one instance.

[]{#built-in-functions-for-flows}

Built-in Functions for Flows
----------------------

`end_to_end_flows` (\<component\>): { \<end\_to\_end\_flow\> } - returns set of end to end flows contained in component instance.

`flow_elements` (\<end\_to\_end\_flow\>): { \<flow\_element\> } - returns set of flow elements, which are connection instances, flow spec instances, or components instances.

`flow_specifications` (\<component\>): { \<flow\_spec\> } - returns set of flow specification instances of a component.


`flow_source` (\<flow\_spec\>): feature - returns the source of a flow specification.

`flow_destination` (\<flow\_spec\>): feature - returns the destination of a flow specification.



[]{#built-in-functions-for-error-models}

Built-in Functions for Error Models
---------------------

`error_state_reachable` (\<component\>, \<state: string\>): Boolean - true

`propagate_error` (\<component\>, \<error\_type: string\>): Boolean -
true if the component instance propagates out the error type on any of its features
if the error state of the component instance is reachable by an incoming transition

`receive_error` (\<component\>, \<error\_type: string\>): Boolean - true
true if the component instance receives the error type of a propagated error on any of its features. Not supported yet.

`contain_error` (\<component\>, \<error\_type: string\>): Boolean - true
if the the component instance has an error event with the specified error type. Not supported yet.

[]{#external-functions}

External Functions
------------------

`analysis` (\<function: string\>, \<args\>): \<ResoluteValue\> - invocation of a Java
function registered as an external function extension point. The
function is specified as string identifier of the extension point. The
arguments are additional parameters of the analysis function. 

The return value must be one of the ResoluteValue subclasses: Boolvalue, IntValue, ListValue, NamedElementValue, RangeValue, RealValue, ResoluteRecordValue, SetValue, StringValue.

[]{#debug-functions}

Debug Functions
------------------

`debug` (\<args\>): true - writes one or more arguments (strings and other base types, sets, lists, model elements as names) to the console.   

> The debug trace is written to a console in OSATE.

> You can enable and disable debug tracing through commands in the context menu of the Assurance Case View that comes with Resolute.

[]{#common-resolute-function-library}

Common Resolute Function Library
================================

[]{#binding-related-functions}

Binding Related Functions
-----------------------------------

`bound`(\<component, binding\_target\>): Boolean - true if the component
instance is bound to the binding target by actual processor, memory, or
connection binding. Note: `bound` is the same as the built-in
`is_bound_to` function.

`processor_bound`(\<component\>, \<binding\_target\>): Boolean - true if the
component instance is bound to the binding target by actual processor
binding

`memory_bound`(\<component\>, \<binding\_target\>): Boolean - true if the
component instance is bound to the binding target by actual memory
binding

`connection_bound`(\<component\>, \<binding\_target\>): Boolean - true if the
component instance is bound to the binding target by actual connection
binding.

> Note: You may want to implement a `function_bound` function. See *Resolute_Stdlib.aadl* for how this can be done by mirroring *processor_bound*.


[]{#connection-related-functions}

Connection Related Functions
----------------------------

`connected`(\<source component\>, \<connection\>, \<destination component\>): Boolean - returns true if the components are the soruce and destination components of the connection..

`source_component`(\<connection\>): component - returns the component
that is the source of the connection instance. This component contains
the feature instance as a connection end point.

`destination_component`(\<connection\>): component - returns the
component that is the destination of the connection instance. This
component contains the feature instance as a connection end point.

`is_port_connection`(\<connection\>): Boolean - true if the connection
is a connection between ports

`is_data_port_connection`(\<connection\>): Boolean - true if one of the
connection end points is a data port. Note: should be determined by the
destination.

`is_event_port_connection`(\{connection\>): Boolean - true if one of
the connection end points is an event port. Note: should be determined
by the destination.

`is_event_data_port_connection`(\<connection\>): Boolean - true if one
of the connection end points is an event data port. Note: should be
determined by the destination.

`is_data_access_connection`(\<connection\>): Boolean - true if one of
the connection end points is a data access feature. 

[]{#model-element-containment}

Model Element Containment
-------------------------

`contained`(\<named\_element\>, \<container\_component\>): Boolean - true if
the named element is contained in the container component. The named
element can be a component or feature instance. Note: also works for
connection instance.

`containing_component`(\<named\_element\>): component - returns the
directly containing component instance. The named element can be a
component or feature instance. Note: also works for connection instance.

[]{#handling-of-feature-groups}

Handling of Feature Groups
--------------------------

Feature groups are represented in the instance model as a hierarchy of
feature instances reflecting the nesting of the feature group.

`flatten_feature`(\<feature\>): {feature} - returns a set of feature
instances that are the leaf elements of a given feature instance. If no
elements are contained in the feature instance, the feature instance
itself is returned as a set.

`flatten_features`( \<{feature}\> ) : {feature} - returns a set of
feature instances that are the leaf elements of a given feature instance
set.

[]{#resolute-examples}

Resolute Examples
=================

[]{#debugging-models-with-resolute}

Debugging Models with Resolute
------------------------------

Get a model element trace on the Console View.  You enable and disable console logging through a command in the context menu of the Assurance Case View of Resolute. Once enabled the built-in function **debug** will write to the console when executed. 

The built-in function **debug** takes one or more parameters and writes them out. It can handle any Resolute base type, lists and sets, as well as AADL model elements.

~~~ {.resolute caption="Model Element Trace in the Assurance View"}
  	let conns : {connection} = connections;
  	debug( "Set of connections ",conns)
~~~

Get a trace recorded as a result object in the Assurance Case View of Resolute we use claims functions as the examples shown below :

~~~ {.resolute caption="Model Element Trace in the Assurance View"}

-- record a model element in the result structure
record_aadl(a : aadl) <=
    ** a **
    true

-- record a set of model elements in the result structure
record_set(s : {aadl}) <=
    ** s **
    true

-- record a list of model elements in the result structure
record_list(s : [aadl], msg: string) <=
	 ** msg ": "s **
	 true
~~~
[]{#reachable-model-elements}


Reachable Model Elements
---------------------------------------

This is a set of functions that collect AADL components that are directly or indirectly reachable via connections.

~~~ {.resolute caption="Reachability Example"}
-- return set of reachable components that have no outgoing connections. 
reachable_endpoints(c : component) : {component} =
	  let outconns: {connection} = outgoing_component_connections(c);
	  if  outconns = {}
	  then {c} 
 	  else  
  		{c2 for (conn : outconns) (c2 : reachable_endpoints(destination_component(conn)))}


-- all components that are reachable via port or data access connections from the given component
-- we consider direct and indirect reachability
-- the use of a set ensures that we do not recurse forever
all_reachable_components(c : component) : {component} =
    recusive_reach({c})

-- support method to expand on a set of reachable components by the next directly rachable components
-- the use of a set ensures that we do not recurse forever
recusive_reach(curr: {component}) : {component} =
	let next_ones : {component} = {y for (x : curr) (y : directly_reachable_components(x))};
    let next_new_ones : {component} = {ele for (ele : next_ones)| not(member(ele,curr))};
    if (next_new_ones = {} ) then
    	curr
    else 
    	union(curr, recusive_reach(next_new_ones))


	-- return set of components that are directly reachable via outgoing connections.
	directly_reachable_components(comp: component):{component} =
	let outconns : {connection} = outgoing_component_connections(comp);
	{otherend for (conn: outconns) (otherend: other_connection_end(conn,comp))}

	  
	-- return all outgoing connections
	-- in the case of data access conneciton we need to consider acces rights to determine the direction
	-- in the case of port connecitons we need to filter out in/out ports with incoming connections.
	outgoing_component_connections(comp: component): {connection} =
	{ conn for (conn: connections(comp)) | 
		if (is_access_connection(conn)) then
		-- access feature has write access
			source(conn) instanceof access and has_write_access(source(conn)) or 
			destination(conn) instanceof access and has_write_access(destination(conn)) 
		else
		-- we have a directed feature connection
		-- it is not an incoming connection with the source an out only feature
			not(destination_component(conn) = comp 
			and direction((feature)source(conn)) = "out"
		)}

   -- For a given connection and a given component return the component on the other end of the connection
   -- This funciton is useful to deal with access connections or bi-directional port connections
   -- where the source could be either end.
   other_connection_end(conn: connection,comp: component): component =
   		if (destination_component(conn) = comp) 
		then
			source_component(conn)
   		else
   			destination_component(conn)

~~~


[]{#copyright}

Copyright
=========

Copyright 2015-18 Carnegie Mellon University

This material is based upon work funded and supported by the Department
of Defense under Contract No. FA8721-05-C-0003 with Carnegie Mellon
University for the operation of the Software Engineering Institute, a
federally funded research and development center.

Any opinions, findings and conclusions or recommendations expressed in
this material are those of the author(s) and do not necessarily reflect
the views of the United States Department of Defense.

NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING
INSTITUTE MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON
UNIVERSITY MAKES NO WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED,
AS TO ANY MATTER INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR
PURPOSE OR MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF
THE MATERIAL. CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF
ANY KIND WITH RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT
INFRINGEMENT.

This material has been approved for public release and unlimited
distribution.

DM-0002203

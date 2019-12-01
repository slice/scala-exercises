# `cats.data.State[S, A]`

From 4.9 of [Scala with Cats](https://underscore.io/books/scala-with-cats/).

> `State` allows us to pass additional state around as part of a computation.
> We define `State` instances representing atomic state operations and thread
> them together using `map` and `flatMap`. In this way we can model mutable
> state in a purely functional way, without using mutation.

`State` bundles both **state** and the **result of a computation** into one
type.

## Model

Instances of `State[S, A]` are represented by functions of type `S => (S, A)`.

- `S`: The type of the state.
- `A`: The type of the result of the computation.

The function can be thought of as transforming the input state into an output
state (`S`), and computing a result (`A`). It boils down to two things:

- transforming an input state to an output state;
- computing a result.

## Running

All run methods return an instance of `Eval`.

### `run`

```scala
val (state, result) = state.run(initialState).value
```

### `runS`

```scala
// Only returns the state (`S`) after running.
val state = state.runS(initialState).value
```

### `runA`

```scala
// Only returns the result (`A`) after running.
val result = state.runA(initialState).value
```

## Composing and Transforming

```scala
// These `State` objects receive the input state and perform a binary operation
// on it, calculating the next state. The result is a `String` describing the
// result alongside the `State`'s name in text.

val addOne = State[Int, String] { input =>
  val result = input + 1
  (result, s"addOne: $result")
}

val double = State[Int, String] { input =>
  val result = input * 2
  (result, s"double: $result")
}

// Using a `for` comprehension to compose both `State`s into one `State`:

val bothState = for {
  addOneResult <- addOne
  doubleResult <- double
} yield (addOneResult, doubleResult)

// This is equivalent to:

val bothState2 =
  addOne.flatMap { addOneResult =>
    double.map { doubleResult =>
      (addOneResult, doubleResult)
    }
  }

val (state, result) = bothState.run(1).value
// Or: val (state, result) = bothState2.run(1).value

// state: Int = 4
// result: (String, String) = ("addOne: 2", "double: 4")
```

## Convenience Constructors

These are mostly used in composition.

"Returning" refers to the resulting value (`A`). For example, returning `5`
would mean that the result would be `5`.

### `State.get`

Returns the state.

```scala
val state = State.get[Int]

state.run(10).value
// (10, 10)
```

### `State.set`

Sets the state, returning `()`.

```scala
val state = State.set[String]("state")

state.run("ignored initial state").value
// ("state", ())
```

### `State.pure`

Returns a value, keeping the state unchanged.

```scala
val state = State.pure[String, String]("result")

state.run("initial state").value
// ("initial state", "result")
```

### `State.inspect`

Transforms the input state using a function, leaving the state unchanged.

```scala
val state = State.inspect[String, String](state => state + "!")

state.run("initial state").value
// ("initial state", "initial state!")
```

### `State.modify`

Transforms the input state using a function and returns `()`.

```scala
val state = State.modify[Int](_ + 1)

state.run(5)
// (6, ())
```

### Example Usage

```scala
val state =
  for {
    // Grab the initial state just for safekeeping.
    beforeAdd <- State.get[Int]

    // Add one to the state.
    added <- State.inspect[Int, Int](_ + 1)

    // Make the (state + 1) value our new state.
    _ <- State.set[Int](added)
  } yield (beforeAdd, added)

val (resultingState, (beforeAdd, added)) = state.run(1).value
println(s"Resulting state: $resultingState")
println(s"Value before adding: $beforeAdd, after adding: $added")
```

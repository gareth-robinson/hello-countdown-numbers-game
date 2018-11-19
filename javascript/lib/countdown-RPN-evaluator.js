// in Countdown, the result is never allowed to be a fraction or negative
const allowedCheck = n => Number.isInteger(n) && n >= 0

function calculate(first, second, operand) {
  let result;
  switch(operand) {
    case '+':
      result = first + second
      break
    case '*':
      result = first * second
      break
    case '-':
      result = first - second
      break
    case '/':
      result = first / second
      break
  }
  return {result, allowed: allowedCheck(result)}
}

function evaluator(symbols) {
  const stack = []
  const allAllowed = symbols.every(s => {
    if (typeof s === 'number') {
      stack.push(s)
      return true
    }

    const second = stack.pop()
    const first = stack.pop()
    const {result, allowed} = calculate(first, second, s)
    stack.push(result)
    return allowed
  })
  return {
    allowed: allAllowed,
    result: allAllowed ? stack.pop() : undefined
  }
}

module.exports = evaluator

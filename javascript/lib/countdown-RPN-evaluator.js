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

function evaluator(symbols, last) {
  const stack = []
  const length = last ? last+1 : symbols.length
  let allAllowed

  for (let i=0; i<length; i++) {
    const s = symbols[i]
    if (typeof s === 'number') {
      stack.push(s)
      continue;
    }

    const second = stack.pop()
    const first = stack.pop()
    const {result, allowed} = calculate(first, second, s)
    allAllowed = allowed;
    if (!allowed) break;
    stack.push(result)
  }
  return {
    allowed: allAllowed,
    result: allAllowed ? stack.pop() : undefined
  }
}

module.exports = evaluator
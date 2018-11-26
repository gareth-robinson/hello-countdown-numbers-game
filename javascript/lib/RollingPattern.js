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

function evaluate(symbols, operand) {
  const rollingSymbols = [].concat(symbols)
  const second = rollingSymbols.pop()
  const first = rollingSymbols.pop()
  const {result, allowed} = calculate(first, second, operand)
  rollingSymbols.push(result)
  return {allowed, result, rollingSymbols}
}

class RollingPattern {
  constructor(symbols, rollingSymbols, result) {
    this.symbols = symbols
    this.rollingSymbols = rollingSymbols
    this.runningTotal = result
  }

  canExtendWithOperand() {
    return this.rollingSymbols.length > 1
  }

  extend(symbol) {
    const extendedSymbols = this.symbols.concat(symbol)
    if (Number.isInteger(symbol)) {
      return new RollingPattern(
        extendedSymbols,
        this.rollingSymbols.concat(symbol),
        symbol
      );
    }
    const {allowed, result, rollingSymbols} = evaluate(this.rollingSymbols, symbol)
    if (allowed && result) {
      return new RollingPattern(
        extendedSymbols,
        rollingSymbols,
        result
      );
    }
  }
}

module.exports = RollingPattern

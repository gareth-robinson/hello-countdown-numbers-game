class Pattern {
  constructor(evaluator, symbols, unusedNumbersInStack) {
    this.evaluator = evaluator
    this.unusedNumbersInStack = unusedNumbersInStack || 0
    this.symbols = symbols || []

    const {result, allowed} = evaluator(this.symbols)
    this.runningTotal = result
    this.allowed = allowed
  }

  canExtendWithOperand() {
    return this.unusedNumbersInStack > 1
  }

  canContinue() {
    return this.allowed
  }

  extend(symbol) {
    const nextUnusedNumbersInStack = Number.isInteger(symbol)
        ? this.unusedNumbersInStack + 1
        : this.unusedNumbersInStack - 1
    return new Pattern(this.evaluator, this.symbols.concat(symbol), nextUnusedNumbersInStack)
  }
}

module.exports = Pattern

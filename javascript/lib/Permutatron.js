const RollingPattern = require('./RollingPattern')
const OPERANDS = ['+', '*', '-', '/']

class Permutatron {
  constructor(numbers, target) {
    this.numbers = numbers
    this.target = target
  }

  extend(pattern, symbol, remaining) {
    const newPattern = pattern.extend(symbol)
    if (newPattern) {
      if (newPattern.runningTotal === this.target) {
        this.found = newPattern
      } else {
        this.iterate(newPattern, remaining)
      }
    }
  }

  iterate(pattern, remaining) {
    const canExtendWithOperand = pattern.canExtendWithOperand()
    for (let i=0; (canExtendWithOperand && i<OPERANDS.length && !this.found); i++) {
      this.extend(pattern, OPERANDS[i], remaining)
    }

    for (let i=0; i<remaining.length && !this.found; i++) {
      const remainingSubset = [].concat(remaining)
      const extract = remainingSubset.splice(i, 1)[0]
      this.extend(pattern, extract, remainingSubset)
    }
  }

  find() {
    this.iterate(new RollingPattern(), this.numbers)
    return this.found
  }
}

module.exports = Permutatron

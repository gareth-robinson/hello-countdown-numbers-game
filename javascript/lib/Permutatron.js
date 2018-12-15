const evaluator = require('./countdown-RPN-evaluator')
const OPERANDS = ['+', '*', '-', '/']

class Permutatron {
  constructor(numbers, target) {
    this.numbers = numbers
    this.target = target
  }

  evaluate(pattern, writePos) {
    const {allowed, result} = evaluator(pattern, writePos)
    if (result === this.target) {
      this.found = pattern
    }
    return allowed
  }

  iterate(pattern, remaining, writePos, unresolvedNumbers) {
    if (unresolvedNumbers > 1) {
      for (let i=0; (i<OPERANDS.length && !this.found); i++) {
        pattern[writePos] = OPERANDS[i]
        const allowed = this.evaluate(pattern, writePos)
        if (!this.found && allowed) {
          this.iterate(pattern, remaining, writePos + 1, unresolvedNumbers - 1)
        }
      }
    }

    for (let i=0; i<remaining.length && !this.found; i++) {
      const remainingSubset = new Array(remaining.length - 1)
      for (let j=0; j<remaining.length; j++) {
        if (j === i) continue;
        remainingSubset[j > i ? j - 1 : j] = remaining[j]
      }
      const extract = remaining[i]
      pattern[ writePos ] = extract
      this.iterate(pattern, remainingSubset, writePos + 1, unresolvedNumbers + 1)
    }
  }

  find() {
    const pattern = new Array(2 * this.numbers.length - 1)
    this.iterate(pattern, this.numbers, 0, 0)
    return this.found
  }
}

module.exports = Permutatron

const Pattern = require('../lib/Pattern')
const fakeEvaluator = symbols => ({result: symbols, allowed: 'test'})
const pattern = new Pattern(fakeEvaluator)

test('extend adds symbols to the stack', () => {
  expect(pattern.extend(1).extend(2).extend(3).symbols).toEqual([1, 2, 3])
})

test('runningTotal contains the result of the evaluator', () => {
  expect(pattern.extend(1).extend(2).extend(3).runningTotal).toEqual([1, 2, 3])
})

test('at least 2 numbers needed before an operand is allowed', () => {
  expect(pattern.extend(1).canExtendWithOperand()).toBe(false)
  expect(pattern.extend(1).extend(1).canExtendWithOperand()).toBe(true)
})

test('an operand is allowed if more than two numbers remain in the stack', () => {
  expect(pattern.extend(1).extend(1).extend(1).extend('+').canExtendWithOperand()).toBe(true)
  expect(pattern.extend(1).extend(1).extend(1).extend('+').extend('+').canExtendWithOperand()).toBe(false)
})

test('allowed contains the allowed of the evaluator', () => {
  expect(pattern.extend(1).allowed).toBe('test')
})

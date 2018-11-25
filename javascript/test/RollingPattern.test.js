const RollingPattern = require('../lib/RollingPattern')
const pattern = new RollingPattern()

test('extend with number adds numbers to the stacks', () => {
  const nextPattern = pattern.extend(1).extend(2).extend(3)
  expect(nextPattern.symbols).toEqual([1, 2, 3])
  expect(nextPattern.rollingSymbols).toEqual([1, 2, 3])
})

test('at least 2 numbers needed before an operand is allowed', () => {
  expect(pattern.extend(1).canExtendWithOperand()).toBe(false)
  expect(pattern.extend(1).extend(1).canExtendWithOperand()).toBe(true)
})

test('an operand is allowed if more than two numbers remain in the stack', () => {
  expect(pattern.extend(1).extend(1).extend(1).extend('+').canExtendWithOperand()).toBe(true)
  expect(pattern.extend(1).extend(1).extend(1).extend('+').extend('+').canExtendWithOperand()).toBe(false)
})

test('extend with symbol stores the full pattern and evaluates on the rolling stack (plus)', () => {
  const nextPattern = pattern.extend(1).extend(2).extend(3).extend('+')
  expect(nextPattern.symbols).toEqual([1, 2, 3, '+'])
  expect(nextPattern.rollingSymbols).toEqual([1, 5])
})

test('extend with symbol stores the full pattern and evaluates on the rolling stack (minus)', () => {
  const nextPattern = pattern.extend(1).extend(5).extend(2).extend('-')
  expect(nextPattern.symbols).toEqual([1, 5, 2, '-'])
  expect(nextPattern.rollingSymbols).toEqual([1, 3])
})

test('extend with symbol stores the full pattern and evaluates on the rolling stack (divide)', () => {
  const nextPattern = pattern.extend(1).extend(8).extend(2).extend('/')
  expect(nextPattern.symbols).toEqual([1, 8, 2, '/'])
  expect(nextPattern.rollingSymbols).toEqual([1, 4])
})

test('extend with symbol stores the full pattern and evaluates on the rolling stack (multiply)', () => {
  const nextPattern = pattern.extend(1).extend(5).extend(2).extend('*')
  expect(nextPattern.symbols).toEqual([1, 5, 2, '*'])
  expect(nextPattern.rollingSymbols).toEqual([1, 10])
})

test('extend returns nothing if the result of evaluation was a fraction', () => {
  const nextPattern = pattern.extend(1).extend(5).extend(2).extend('/')
  expect(nextPattern).toEqual(undefined)
})

test('extend returns nothing if the result of evaluation was a negative', () => {
  const nextPattern = pattern.extend(1).extend(2).extend(5).extend('-')
  expect(nextPattern).toEqual(undefined)
})

test('pattern runningTotal is the result of the last evaluation, if an operand was added', () => {
  const nextPattern = pattern.extend(1).extend(2).extend('+').extend(3).extend('*')
  expect(nextPattern.runningTotal).toEqual(9)
})

test('pattern runningTotal is the result of the last number, if a number was added', () => {
  const nextPattern = pattern.extend(1).extend(2).extend('+').extend(5)
  expect(nextPattern.runningTotal).toEqual(5)
})

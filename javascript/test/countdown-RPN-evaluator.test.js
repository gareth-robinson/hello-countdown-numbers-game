const countdownRPNEvaluator = require('../lib/countdown-RPN-evaluator')

test('plus adds', () => {
  expect(countdownRPNEvaluator([1,2,'+'])).toEqual({allowed:true, result:3})
})

test('minus subtracts', () => {
  expect(countdownRPNEvaluator([7,3,'-'])).toEqual({allowed:true, result:4})
})

test('slash divides', () => {
  expect(countdownRPNEvaluator([10,2,'/'])).toEqual({allowed:true, result:5})
})

test('star multiplies', () => {
  expect(countdownRPNEvaluator([2,3,'*'])).toEqual({allowed:true, result:6})
})

test('evaluator returns last number on the stack', () => {
  expect(countdownRPNEvaluator([2,3,7])).toEqual({allowed:true, result:7})
})

test('evaluation pushes onto the stack', () => {
  expect(countdownRPNEvaluator([1,3,'+',2,'*'])).toEqual({allowed:true, result:8})
})

test('fractional result from divide is not allowed', () => {
  expect(countdownRPNEvaluator([6,4,'/'])).toEqual({allowed:false, result: undefined})
})

test('negative result from subtract is not allowed', () => {
  expect(countdownRPNEvaluator([6,4,'/'])).toEqual({allowed:false, result: undefined})
})
function rpnToInfix(pattern) {
  const {symbols} = pattern
  const stack = []

  symbols.forEach((s, i) => {
    if (typeof s === 'number') {
      stack.push(s)
    } else {
      const second = stack.pop()
      const first = stack.pop()
      const block = `${first} ${s} ${second}`
      const wrapped = i === symbols.length - 1
        ? block
        : `(${block})`
      stack.push(wrapped)
    }
  })
  return stack.pop()
}

module.exports = rpnToInfix

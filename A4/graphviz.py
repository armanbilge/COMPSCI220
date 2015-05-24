import sys

print('digraph {')
for i, l in enumerate(sys.stdin):
    for j in map(int, l.split(',')):
        print('{} -> {};'.format(i, j))
print('}')

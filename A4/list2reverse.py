"""
list2reverse.py
Arman Bilge
8079403
COMPSCI 220
Assignment 4
Part 2
"""

with open('list.txt') as f:
    graph = [list(map(int, l.split(','))) for l in f]
with open('reverselist.txt', 'w') as f:
    for i in range(len(graph)):
        print(','.join(str(j) for j, a in enumerate(graph) if i in a), file=f)

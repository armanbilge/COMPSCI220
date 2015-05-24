"""
matrix2list.py
Arman Bilge
8079403
COMPSCI 220
Assignment 4
Part 1
"""

with open('matrix.txt') as f1, open('list.txt', 'w') as f2:
    for l in f1:
        print(','.join(str(i) for i, a in enumerate(map(int, l.split(','))) if a), file=f2)

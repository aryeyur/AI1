'''
This file should be runnable to print map_statistics using 
$ python stats.py
'''

from collections import namedtuple, Counter
from ways import load_map_from_csv


def map_statistics(roads):
    '''return a dictionary containing the desired information
    You can edit this function as you wish'''
    Stat = namedtuple('Stat', ['max', 'min', 'avg'])

    return {
        'Number of junctions': len(roads.junctions()),
        'Number of links': sum(1 for x in roads.iterlinks()),
        'Outgoing branching factor': Stat(max=max(len(x.links) for x in roads.junctions()),
                                          min=min(len(x.links) for x in roads.junctions()),
                                          avg=sum(len(x.links) for x in roads.junctions()) / sum(
                                              1 for x in roads.junctions())),
        'Link distance': Stat(max=max(x.distance for x in roads.iterlinks()),
                              min=min(x.distance for x in roads.iterlinks()),
                              avg=sum(x.distance for x in roads.iterlinks()) / sum(1 for x in roads.iterlinks())),
        # value should be a dictionary
        # mapping each road_info.TYPE to the no' of links of this type
        'Link type histogram': Counter(x.highway_type for x in roads.iterlinks()).items(),
    # tip: use collections.Counter
    }


def print_stats():
    for k, v in map_statistics(load_map_from_csv()).items():
        print('{}: {}'.format(k, v))


if __name__ == '__main__':
    from sys import argv

    assert len(argv) == 1
    print_stats()

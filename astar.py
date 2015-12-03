import sys
from collections import namedtuple
from ways import load_map_from_csv, compute_distance
from ways.info import SPEED_RANGES
from ways.tools import base_traffic_pattern
from pqdict import pqdict

Node = namedtuple('Node', ['junction', 'parent', 'g_value', 'h_value', 'f_value', ])

roads = None


def get_best_node(node_list):
    min_val = sys.maxsize
    best_node = None
    for current in node_list:
        if current.f_value < min_val:
            best_node = current
            min_val = current.f_value
    return best_node


def children_of_junction(junction):
    global roads
    children = list()
    for lnk in junction.links:
        if lnk.source == junction.index:
            children.append(roads.junctions()[lnk.target])
    return children


def price_function(source_junction, target_junction, t0):
    global roads
    for lnk in source_junction.links:
        if lnk.target == target_junction.index:
            return (lnk.distance / 1000) / roads.realtime_link_speed(lnk, t0)
    return sys.maxsize


def heuristic_function(source_junction, target_junction, t0):
    return compute_distance(source_junction.lat, source_junction.lon, target_junction.lat,
                            target_junction.lon) / 110  # 110 is the highest speed


def format_result(last_node):
    top = last_node
    all_indices = list()
    while top is not None:
        all_indices.append(top.junction.index)
        top = top.parent
    all_indices.reverse()
    return all_indices


def comparator_f(node1, node2):
    return node1.f_value < node2.f_value


def run_astar(source_junction_index, target_junction_index, t0, price_func=price_function,
              heuristic_func=heuristic_function):
    global roads
    roads = load_map_from_csv()
    source_junction = roads.junctions()[source_junction_index]
    target_junction = roads.junctions()[target_junction_index]
    closed_list = dict()
    open_pqdict = pqdict(precedes=comparator_f)

    source_h_value = heuristic_func(source_junction, target_junction, t0)
    open_pqdict.additem(source_junction_index, Node(source_junction, None, 0, source_h_value, source_h_value))

    while open_pqdict:  # while list is not empty
        best_node = open_pqdict.popitem()[1]
        closed_list[best_node.junction.index] = best_node
        if best_node.junction.index == target_junction_index:
            return format_result(best_node)
        for son in children_of_junction(best_node.junction):
            son_g_value = price_func(best_node.junction, son, t0) + best_node.g_value
            son_h_value = heuristic_func(son, target_junction, t0)
            if son.index in open_pqdict.keys():
                if open_pqdict[son.index].g_value > son_g_value:
                    son_copy = Node(son, best_node, son_g_value, son_h_value,
                                    son_h_value + son_g_value)
                    open_pqdict.updateitem(son.index, son_copy)
                continue

            if son.index in closed_list:
                if closed_list[son.index].g_value > son_g_value:
                    son_copy = Node(son, best_node, son_g_value, son_h_value,
                                    son_h_value + son_g_value)
                    del closed_list[son.index]
                    open_pqdict.additem(son_copy.junction.index, son_copy)
                continue
            open_pqdict.additem(son.index, Node(son, best_node, son_g_value, son_h_value, son_g_value + son_h_value))
    return None

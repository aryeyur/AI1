import sys
from collections import namedtuple
from ways import load_map_from_csv, compute_distance
from ways.info import SPEED_RANGES
from ways.tools import base_traffic_pattern
from pqdict import pqdict

# region common
Node = namedtuple('Node', ['junction', 'parent', 'g_value', 'h_value', 'f_value', ])
global roads
roads = None
global roads_junctions
roads_junctions = None


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


# endregion

# region simple A*

def price_function(lnk, t0):
    global roads
    return (lnk.distance / 1000) / roads.realtime_link_speed(lnk, t0)


def heuristic_function(source_junction, target_junction):
    return compute_distance(source_junction.lat, source_junction.lon, target_junction.lat,
                            target_junction.lon) / 110  # 110 is the highest speed


def run_astar(source_junction_index, target_junction_index, t0, price_func=price_function,
              heuristic_func=heuristic_function):
    global roads
    roads = load_map_from_csv()
    global roads_junctions
    roads_junctions = roads.junctions()
    source_junction = roads_junctions[source_junction_index]
    target_junction = roads_junctions[target_junction_index]
    closed_list = dict()
    open_pqdict = pqdict(precedes=comparator_f)

    source_h_value = heuristic_func(source_junction, target_junction)
    open_pqdict.additem(source_junction_index, Node(source_junction, None, 0, source_h_value, source_h_value))

    while open_pqdict:  # while pqdict is not empty
        best_node = open_pqdict.popitem()[1]
        closed_list[best_node.junction.index] = best_node
        if best_node.junction.index == target_junction_index:
            return format_result(best_node)
        for lnk_to_son in best_node.junction.links:
            son = roads_junctions[lnk_to_son.target]
            son_g_value = price_func(lnk_to_son, t0) + best_node.g_value
            son_h_value = heuristic_func(son, target_junction)
            if son.index in open_pqdict.keys():
                if open_pqdict[son.index].g_value > son_g_value:
                    son_copy = Node(son, best_node, son_g_value, son_h_value, son_h_value + son_g_value)
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


# endregion

# region A* with time

def price_function_with_time(lnk, arrival_time):
    global roads
    return (lnk.distance / 1000) / roads.link_speed_history(lnk, arrival_time)


def run_astar_with_time(source_junction_index, target_junction_index, t0, price_func=price_function_with_time,
                        heuristic_func=heuristic_function):
    global roads
    roads = load_map_from_csv()
    global roads_junctions
    roads_junctions = roads.junctions()
    source_junction = roads_junctions[source_junction_index]
    target_junction = roads_junctions[target_junction_index]
    closed_list = dict()
    open_pqdict = pqdict(precedes=comparator_f)

    source_h_value = heuristic_func(source_junction, target_junction)
    open_pqdict.additem(source_junction_index, Node(source_junction, None, 0, source_h_value, source_h_value))

    while open_pqdict:  # while pqdict is not empty
        best_node = open_pqdict.popitem()[1]
        closed_list[best_node.junction.index] = best_node
        if best_node.junction.index == target_junction_index:
            return format_result(best_node)
        for lnk_to_son in best_node.junction.links:
            son = roads_junctions[lnk_to_son.target]
            son_g_value = price_func(lnk_to_son, (best_node.g_value + t0) % 1440) + best_node.g_value
            son_h_value = heuristic_func(son, target_junction)
            if son.index in open_pqdict.keys():
                if open_pqdict[son.index].g_value > son_g_value:
                    son_copy = Node(son, best_node, son_g_value, son_h_value, son_h_value + son_g_value)
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

# endregion
